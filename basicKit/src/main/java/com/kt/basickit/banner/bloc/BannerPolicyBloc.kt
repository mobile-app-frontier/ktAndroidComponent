package com.kt.basickit.banner.bloc

import android.content.Context
import coil.imageLoader
import coil.request.ImageRequest
import com.kt.basickit.banner.LocalBannerPolicy
import com.kt.basickit.banner.MutableLocalBannerPolicy
import com.kt.basickit.banner.domain.entity.BannerPolicy
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
import java.lang.Error
import java.util.Calendar
import java.util.Date

public class BannerPolicyBloc(
    private val context: Context,
    private val repository: BannerPolicyRepository,
    private val localBannerPolicyGetter: suspend () -> LocalBannerPolicy,
    private val localBannerPolicySetter: suspend (LocalBannerPolicy) -> Unit,
    appVersion: String
) {
    private val mutableState = MutableStateFlow<BannerPolicyState>(BannerPolicyState.Initial)
    private val appVersion = Version.createVersion(appVersion)

    public val state: StateFlow<BannerPolicyState>
        get() = mutableState.asStateFlow()

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
            } catch (e: Error) {
                mutableState.value = BannerPolicyState.Fail(error = e)
            }
        }
    }

    private suspend fun handleBannerPolicy(remoteBanner: BannerPolicy, localBanner: LocalBannerPolicy) {
        // default banner filtering
        val filteredDefaultBanner = filterValidRemoteBannerPolicy(remoteBanner.defaultBanner)

        // popup banner filtering
        val filteredPopupBanner = filterValidBannerPolicy(remoteBanner.popupBanner, localBanner)

        // local 정책 update
        localBannerPolicySetter(filteredPopupBanner.second)

        mutableState.value = BannerPolicyState.Success(
            willShowBanner = BannerPolicy(
                defaultBanner = filteredDefaultBanner,
                popupBanner = filteredPopupBanner.first
            )
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