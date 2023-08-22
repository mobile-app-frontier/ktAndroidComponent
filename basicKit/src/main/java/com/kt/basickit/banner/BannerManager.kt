package com.kt.basickit.banner

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.kt.basickit.R
import com.kt.basickit.banner.domain.entity.BannerLandingType
import com.kt.basickit.banner.domain.entity.BannerPolicy
import com.kt.basickit.banner.domain.entity.PopupBannerPolicy
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem
import com.kt.basickit.banner.view.defaultBanner.DefaultBannerView
import com.kt.basickit.banner.view.popupBanner.PopupBannerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
//    private var isStarted = false // Android 의 경우, 화면을 회전할 경우 Activity 가 재시작 되므로 LaunchedEffect 를 사용 하더 라도 function 이
                                    // 여러번 호출됨. 따라서 Singleton 인 BannerManager 에서 이를 직접 관리함.
                                    // -> 가로 모드 UI 제공이 쉽지 않아 가로 모드는 제공 하지 않음. 따라서 현재 사용 하지 않음.

    private var bannerPolicy: BannerPolicy? = null

    // Coroutine
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    // Local Banner Policy
    private var localBannerPolicyGetter: (suspend () -> LocalBannerPolicy)? = null
    private var localBannerPolicySetter: (suspend (LocalBannerPolicy) -> Unit)? = null
    // 단말에 저장 되어 있는 LocalBannerPolicy + 새로 추가된 로컬 정책
    private val localBannerPolicy: MutableLocalBannerPolicy = mutableMapOf()

    // Popup
    // 보여줄 Popup Banner Policy. 이미 보여준 배너는 들어 있지 않음.
    private var willShowPopupBannerPolicy = PopupBannerPolicy()
    private var fragmentManager: FragmentManager? = null

    // Landing
    private val mutableLandingType = MutableSharedFlow<BannerLandingType>()
    public val landingType: SharedFlow<BannerLandingType>
        get() = mutableLandingType.asSharedFlow()

    internal fun sendToLandingType(landingType: BannerLandingType, dismiss: () -> Unit) {
        scope.launch {
            mutableLandingType.emit(landingType)

            if (landingType is BannerLandingType.InApp) {
                willShowPopupBannerPolicy.clear()
                dismiss()
            }
        }

    }

    // 여러 번 호출 될 수 있음. BannerManager 는 Singleton 이므로 App lifecycle 동안 여러번 fetch 될 수 있기 때문에
    public fun initialize(
        bannerPolicy: BannerPolicy?,
        localBannerPolicyGetter: suspend () -> LocalBannerPolicy,
        localBannerPolicySetter: suspend (LocalBannerPolicy) -> Unit
    ) {
        isInitialized = true
//        isStarted = false

        this.bannerPolicy = bannerPolicy

        this.localBannerPolicyGetter = localBannerPolicyGetter
        this.localBannerPolicySetter = localBannerPolicySetter
    }

    @Composable
    public fun DefaultBannerView(category: String, modifier: Modifier = Modifier) {
        if (!isInitialized) { return }

        val defaultBanners = bannerPolicy?.defaultBanner?.get(category) ?: return

        if (defaultBanners.isEmpty()) { return }

        return DefaultBannerView(banners = defaultBanners, modifier = modifier)
    }

    public fun startPopupBanner(context: Context) {
        if (!isInitialized) { return } //|| isStarted) { return }

        val activity = context as? FragmentActivity ?: return
        fragmentManager = activity.supportFragmentManager
//        isStarted = true

        bannerPolicy?.let { bannerPolicy ->
            scope.launch {
                async { localBannerPolicyGetter?.let { it() } }.await()?.let { localBannerPolicy.putAll(it) }
            }

            willShowPopupBannerPolicy = bannerPolicy.popupBanner
            presentPopup()
        }
    }

    // 보여줄 popup banner 중 높은 우선 순위의 popup banner 을 present 함.
    internal fun presentPopup() {
        if (willShowPopupBannerPolicy.isNotEmpty()) {
            willShowPopupBannerPolicy.poll()?.let { popupBanner ->
               fragmentManager?.let {
                    val fragment = PopupBannerFragment(banner = popupBanner)
                    fragment.show(it, fragment.tag)
                }
            }
        }
    }

    internal fun saveLocalBannerPolicy(id: String, notShowedDate: Date) {
        scope.launch {
            localBannerPolicy[id] = notShowedDate.time.toString()

            localBannerPolicySetter?.let { it(localBannerPolicy) }
        }
    }
}