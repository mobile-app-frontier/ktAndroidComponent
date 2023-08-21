package com.kt.basickit.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kt.basickit.banner.domain.entity.BannerLandingType
import com.kt.basickit.banner.domain.entity.BannerPolicy
import com.kt.basickit.banner.domain.entity.PopupBannerPolicy
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem
import com.kt.basickit.banner.view.defaultBanner.DefaultBannerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

public object BannerManager {
    private var isInitialized = false
    private var isStarted = false // Android 의 경우, 화면을 회전할 경우 Activity 가 재시작 되므로 LaunchedEffect 를 사용 하더 라도 function 이
    // 여러번 호출됨. 따라서 Singleton 인 BannerManager 에서 이를 직접 관리함.

    private var bannerPolicy: BannerPolicy? = null

    // Coroutine
    private var scope: CoroutineScope? = null

    // Local Banner Policy
    private var localBannerPolicyGetter: (suspend () -> LocalBannerPolicy)? = null
    private var localBannerPolicySetter: (suspend (LocalBannerPolicy) -> Unit)? = null
    // 단말에 저장 되어 있는 LocalBannerPolicy + 새로 추가된 로컬 정책
    private val localBannerPolicy: MutableLocalBannerPolicy = mutableMapOf()

    // Popup
    // 보여줄 Popup Banner Policy. 이미 보여준 배너는 들어있지 않음.
    private var willShowPopupBannerPolicy = PopupBannerPolicy()
    private var mutablePopupBanner = MutableStateFlow<PopupBannerPolicyItem?>(null)
    internal val popupBanner: StateFlow<PopupBannerPolicyItem?>
        get() = mutablePopupBanner.asStateFlow()

    // Landing
    private val mutableLandingType = MutableSharedFlow<BannerLandingType>()
    public val landingType: SharedFlow<BannerLandingType>
        get() = mutableLandingType.asSharedFlow()

    internal fun sendToLandingType(landingType: BannerLandingType) {
        scope?.launch {
            mutableLandingType.emit(landingType)

            if (landingType is BannerLandingType.InApp) {
                dismiss()
            }
        }

    }

    // 여러 번 호출 될 수 있음. BannerManager 는 Singleton 이므로 App lifecycle 동안 여러번 fetch 될 수 있기 때문에
    public fun initialize(
        scope: CoroutineScope,
        bannerPolicy: BannerPolicy?,
        localBannerPolicyGetter: suspend () -> LocalBannerPolicy,
        localBannerPolicySetter: suspend (LocalBannerPolicy) -> Unit
    ) {
//        if (isInitialized) {
//            return
//        }

        isInitialized = true
        isStarted = false

        this.scope = scope
        this.bannerPolicy = bannerPolicy

        this.localBannerPolicyGetter = localBannerPolicyGetter
        this.localBannerPolicySetter = localBannerPolicySetter
    }

    @Composable
    public fun DefaultBannerView(category: String, modifier: Modifier = Modifier) {
        if (!isInitialized) { return }

        val defaultBanners = bannerPolicy?.defaultBanner?.get(category) ?: return

        return DefaultBannerView(banners = defaultBanners, modifier = modifier)
    }

    public fun startPopupBanner() {
        if (!isInitialized || isStarted) { return }

        isStarted = true

        bannerPolicy?.let { bannerPolicy ->
            scope?.launch {
                async { localBannerPolicyGetter?.let { it() } }.await()?.let { localBannerPolicy.putAll(it) }
            }

            willShowPopupBannerPolicy = bannerPolicy.popupBanner
            presentPopup()
        }
    }

    // 보여줄 popup banner 중 높은 우선 순위의 popup banner 을 present 함.
    private fun presentPopup() {
        if (willShowPopupBannerPolicy.isNotEmpty()) {
            willShowPopupBannerPolicy.poll()?.let { popupBanner ->
                mutablePopupBanner.value = popupBanner
            }
        }
    }

    // 현재 열려 있는 popup banner 를 닫은 후, 보여줄 popup banner 가 있다면 present 함.
    internal fun dismissAndPresentPopup(id: String? = null, notShowedDate: Date? = null) {
        if (id != null && notShowedDate != null) {
            saveLocalBannerPolicy(id, notShowedDate)
        }
        mutablePopupBanner.value = null

        scope?.launch {
            delay(100)
            presentPopup()
        }
    }

    private fun dismiss() {
        mutablePopupBanner.value = null
    }

    private fun saveLocalBannerPolicy(id: String, notShowedDate: Date) {
        scope?.launch {
            localBannerPolicy[id] = notShowedDate.time.toString()

            localBannerPolicySetter?.let { it(localBannerPolicy) }
        }
    }

}