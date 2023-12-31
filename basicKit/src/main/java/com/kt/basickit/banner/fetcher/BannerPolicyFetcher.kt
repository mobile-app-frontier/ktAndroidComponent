package com.kt.basickit.banner.fetcher

import android.content.Context
import coil.imageLoader
import coil.request.ImageRequest
import com.kt.basickit.banner.BannerManager
import com.kt.basickit.banner.LocalBannerPolicy
import com.kt.basickit.banner.MutableLocalBannerPolicy
import com.kt.basickit.banner.data.source.BannerPolicyDataSource
import com.kt.basickit.banner.domain.entity.BannerPolicy
import com.kt.basickit.banner.domain.entity.BannerPolicyImpl
import com.kt.basickit.banner.domain.entity.DefaultBannerPolicy
import com.kt.basickit.banner.domain.entity.PopupBannerPolicy
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem
import com.kt.basickit.banner.domain.repository.BannerPolicyRepository
import com.kt.basickit.util.Version
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar
import java.util.Date

/**
 * 서버 에서 받아온 배너 정책과 단말에 저장된 정책(보여 주지 않을 배너에 대한 정보) 을 가져와
 * 비교 하여 보여줄 배너를 보여줌.
 *
 * @property context
 * @property localBannerPolicyGetter 단말에 저장된 [LocalBannerPolicy] 를 읽어 오는 로직.
 * ex) PreferencesDataStore 에서 특정 키에 저장된 [LocalBannerPolicy] 형태의 value 를 읽어 오는 로직.
 * @property localBannerPolicySetter 단말에 [LocalBannerPolicy] 를 저장 하는 로직.
 * ex) PreferencesDataStore 의 특정 키에 [LocalBannerPolicy] 형태의 value 를 저장 하는 로직.
 * @constructor crete BannerPolicyFetcher
 *
 *
 * @param dataSource
 * @param appVersion
 */
public class BannerPolicyFetcher(
    private val context: Context,
    dataSource: BannerPolicyDataSource,
    private val localBannerPolicyGetter: suspend () -> LocalBannerPolicy,
    private val localBannerPolicySetter: suspend (LocalBannerPolicy) -> Unit,
    appVersion: String
) {
    private val mutableState = MutableStateFlow<BannerPolicyState>(BannerPolicyState.Initial)
    private val appVersion = Version.createVersion(appVersion)
    private val repository = BannerPolicyRepository(dataSource)

    /**
     * [BannerPolicyFetcher] 의 현재 상태.
     */
    public val state: StateFlow<BannerPolicyState>
        get() = mutableState.asStateFlow()

    /**
     * remoteBannerPolicy 와 localBannerPolicy 를 동시에 fetch 하고 비교 하여 보여줄 배너를 filtering 함.
     *
     * - remoteBannerPolicy 와 localBannerPolicy 를 fetch 성공 하면 [state] 는 [BannerPolicyState.Fetched] 상태로 변함.
     * - remoteBannerPolicy 와 localBannerPolicy 를 비교 하여 filtering 한 후,
     * 유효한 로컬 배너 정책을 update & [state] 는 [BannerPolicyState.Success] 상태로 변함.
     * - fetch 과정 중에 [Throwable] 이 발생할 경우, 내부 에서 try-catch 로 처리. [state] 는 [BannerPolicyState.Fail] 상태로 변함.
     */
    public suspend fun fetch() {
        coroutineScope {
            try {
                val remoteBannerPolicy = async {
                    repository.getBannerPolicy()
                }.await()

                val localBannerPolicy = async {
                    localBannerPolicyGetter()
                }.await()

                mutableState.value = BannerPolicyState.Fetched(
                    remoteBanner = remoteBannerPolicy,
                    localBanner = localBannerPolicy
                )
                handleBannerPolicy(remoteBanner = remoteBannerPolicy, localBanner = localBannerPolicy)
            } catch (e: Throwable) {
                mutableState.value = BannerPolicyState.Fail(error = e)
            }
        }
    }

    private suspend fun handleBannerPolicy(remoteBanner: BannerPolicyImpl, localBanner: LocalBannerPolicy) {
        // default banner filtering
        val filteredDefaultBanner = filterValidRemoteBannerPolicy(remoteBanner.defaultBanner)

        // popup banner filtering
        val filteredPopupBanner = filterValidBannerPolicy(remoteBanner.popupBanner, localBanner)

        // local 정책 update
        localBannerPolicySetter(filteredPopupBanner.second)

        val bannerPolicy = BannerPolicyImpl(
            defaultBanner = filteredDefaultBanner,
            popupBanner = filteredPopupBanner.first
        )

        BannerManager.initialize(
            localBannerPolicySetter = localBannerPolicySetter,
            localBannerPolicyGetter = localBannerPolicyGetter,
            bannerPolicy = bannerPolicy
        )

        mutableState.value = BannerPolicyState.Success(
            willShowBanner = bannerPolicy
        )
    }

    private fun filterValidRemoteBannerPolicy(defaultBanner: DefaultBannerPolicy): DefaultBannerPolicy {
        return defaultBanner.mapValues { (_, values) ->
            values
                .filter { equals(it.targetAppVersion, appVersion = appVersion) }
                .sortedByDescending { it.priority }
                .onEach { preloadImage(it.content.url())} // Image Cache
        }
    }

    private fun filterValidBannerPolicy(
        remotePopupBanner: PopupBannerPolicy,
        localBanner: LocalBannerPolicy
    ): Pair<PopupBannerPolicy, LocalBannerPolicy> {
        val filteredPopupBanner = PopupBannerPolicy()
        val updatedLocalBanner: MutableLocalBannerPolicy = mutableMapOf()

        while (remotePopupBanner.isNotEmpty()) {
            remotePopupBanner.poll()?.let { popupBannerItem ->
                // 서버 에서 내려준 배너 정책에 대한 로컬 배너 정책이 저장 되어 있디면 저장.
                localBanner[popupBannerItem.id]?.let {
                    updatedLocalBanner[popupBannerItem.id] = it
                }

                // app version 과 비교 -> 보여줄 배너 라면,
                if (equals(popupBannerItem.targetAppVersion, appVersion)) {
                    // expired date 가 local 에 저장 되지 않았 거나, 만료 기간이 지났 다면,
                    if (isWillShow(notShowUntil = localBanner[popupBannerItem.id]?.toLong())) {
                        filteredPopupBanner.offer(popupBannerItem)

                        // 이미지 타입의 url 일 경우, image cache
                        if (popupBannerItem.content is PopupBannerPolicyItem.Content.Image) {
                            preloadImage(popupBannerItem.content.url)
                        }
                    }
                }
            }
        }

        return Pair(filteredPopupBanner, updatedLocalBanner)
    }

    private fun preloadImage(url: String) {
        val request = ImageRequest.Builder(context)
            .data(url)
            // Optional, but setting a ViewSizeResolver will conserve memory by limiting the size the image should be preloaded into memory at.

            // For example you can set static size, or size resolved with Modifier.onSizeChanged
            // .size(coil.size.PixelSize(width = 100, height = 100))

            // or resolve display size if your images are full screen
            // .size(DisplaySizeResolver(context))
            .build()
        context.imageLoader.enqueue(request)
    }

    private fun equals(version: Version?, appVersion: Version): Boolean {
        return version?.let {
            it == appVersion
        } ?: true
    }

    private fun isWillShow(notShowUntil: Long?): Boolean {
        return notShowUntil?.let {
            Date(it) < Calendar.getInstance().time
        } ?: true
    }
}