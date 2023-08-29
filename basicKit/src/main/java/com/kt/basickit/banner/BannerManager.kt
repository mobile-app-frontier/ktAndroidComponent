package com.kt.basickit.banner

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.kt.basickit.banner.domain.entity.BannerLandingType
import com.kt.basickit.banner.domain.entity.BannerPolicy
import com.kt.basickit.banner.domain.entity.BannerPolicyImpl
import com.kt.basickit.banner.domain.entity.DefaultBannerPolicyItem
import com.kt.basickit.banner.domain.entity.PopupBannerPolicy
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem
import com.kt.basickit.banner.exception.BannerPolicyException
import com.kt.basickit.banner.view.defaultBanner.DefaultBannerFragment
import com.kt.basickit.banner.view.defaultBanner.DefaultBannerView
import com.kt.basickit.banner.view.option.defaultButtonTextStyle
import com.kt.basickit.banner.view.popupBanner.PopupBannerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date

/**
 * PopupBanner Bottom Sheet 를 show/hide 관리와 category 별 DefaultBanner Fragment 를 제공 해주는 역할을 함.
 *
 * <em>BannerPolicyFetcher 를 통해 fetch() 를 완료한 후, BannerPolicyFetcher 의 state 가 Success 일 때만 사용 가능.</em>
 * <em>PopupBanner 의 경우, FragmentActivity 에서만 사용 가능.</em>
 */
public object BannerManager {
    // Public Property

    /**
     * DefaultBanner or PopupBanner 에 landingType 이 지정 되어 있을 경우, user 가 해당 Banner 을 click 하였을 때마다
     * 해당 [BannerLandingType] 을 publish.
     *
     * Landing 하는 기능을 제공 하지 않으 므로, [landingType] 을 collect 하여 직접 처리해야 함.
     */
    public val landingType: SharedFlow<BannerLandingType>
        get() = mutableLandingType.asSharedFlow()

    private var isInitialized = false
    private var isStarted = false // Android 의 경우, 화면을 회전할 경우 Activity 가 재시작 되므로 LaunchedEffect 를 사용 하더 라도 function 이
                                    // 여러번 호출됨. 따라서 Singleton 인 BannerManager 에서 이를 직접 관리함.

    private var bannerPolicy: BannerPolicyImpl? = null

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
    internal var buttonTextStyle: TextStyle = TextStyle.defaultButtonTextStyle()

    // Landing
    private val mutableLandingType = MutableSharedFlow<BannerLandingType>()


    // Public function
    /**
     * category 에 해당 하는 DefaultBannerView(Image Slider) Composable UI 를 제공
     *
     * @param category default banner 의 category
     * @param modifier Composable Modifier
     */
    @Composable
    public fun DefaultBannerView(category: String, modifier: Modifier = Modifier) {
        if (!isInitialized) { throw BannerPolicyException.InvalidState("Failed BannerFetcher") }

        val defaultBanners = bannerPolicy?.defaultBanner?.get(category) ?: return

        if (defaultBanners.isEmpty()) { return }

        return DefaultBannerView(banners = defaultBanners, modifier = modifier)
    }

    /**
     * category 에 해당 하는 Default Banner 들의 ImageSlider UI Fragment 를 제공.
     *
     * @param category default banner 의 category
     * @return ImageSlider UI Fragment
     * @throws [BannerPolicyException.InvalidState] BannerPolicyFetcher fetch 성공 전에 BannerManager 사용시 발생.
     */
    public fun getDefaultBannerFragment(category: String): Fragment {
        if (!isInitialized) { throw BannerPolicyException.InvalidState("Failed BannerFetcher") }

        return DefaultBannerFragment(category)
    }

    /**
     * start PopupBanner bottomSheet show
     *
     * @param context
     * @param buttonTextStyle PopupBanner Button 의 TextStyle.
     * null 일 경우 [TextStyle.Companion.defaultButtonTextStyle] 를 사용.
     *
     * @throws [BannerPolicyException.InvalidState] BannerPolicyFetcher fetch 성공 전에 BannerManager 사용시 발생.
     * @throws [BannerPolicyException.FailToStartPopup] Activity 가 FragmentActivity 가 아닐 경우 발생.
     */
    public fun startPopupBanner(context: Context, buttonTextStyle: TextStyle? = null) {
        if (!isInitialized) { throw BannerPolicyException.InvalidState("Failed BannerFetcher") }

        if (isStarted) { return }

        isStarted = true

        this.buttonTextStyle = buttonTextStyle ?: TextStyle.Companion.defaultButtonTextStyle()

        bannerPolicy?.let { bannerPolicy ->
            scope.launch {
                async { localBannerPolicyGetter?.let { it() } }.await()?.let { localBannerPolicy.putAll(it) }
            }

            willShowPopupBannerPolicy = bannerPolicy.popupBanner
            presentPopup(context = context)
        }
    }

    internal fun sendToLandingType(landingType: BannerLandingType, dismiss: () -> Unit = {}) {
        scope.launch {
            mutableLandingType.emit(landingType)

            if (landingType is BannerLandingType.InApp) {
                willShowPopupBannerPolicy.clear()
                dismiss()
            }
        }

    }

    // 여러 번 호출 될 수 있음. BannerManager 는 Singleton 이므로 App lifecycle 동안 여러번 fetch 될 수 있기 때문에
    internal fun initialize(
        bannerPolicy: BannerPolicyImpl?,
        localBannerPolicyGetter: suspend () -> LocalBannerPolicy,
        localBannerPolicySetter: suspend (LocalBannerPolicy) -> Unit
    ) {
        isInitialized = true
        isStarted = false

        this.bannerPolicy = bannerPolicy

        this.localBannerPolicyGetter = localBannerPolicyGetter
        this.localBannerPolicySetter = localBannerPolicySetter
    }

    internal fun getDefaultBanners(category: String): List<DefaultBannerPolicyItem> {
        return bannerPolicy?.defaultBanner?.get(category) ?: listOf()
    }

    // 보여줄 popup banner 중 높은 우선 순위의 popup banner 을 present 함.
    internal fun presentPopup(context: Context) {
        val activity = context as? FragmentActivity ?: throw BannerPolicyException.FailToStartPopup(
            "Only FragmentActivity Can Use BannerManager"
        )
        val fragmentManager = activity.supportFragmentManager

        if (willShowPopupBannerPolicy.isNotEmpty()) {
           fragmentManager.let {
               val fragment = PopupBannerFragment()
               try {
                   fragment.show(it, fragment.tag)
               } catch (e: Throwable) {
                   // 화면 회전시 Activity 가 소멸 하므로, 화면 회전 시의 Fragment onDetach() 에서 해당 function 을 call 했을 경우,
                   // fragment.show 에서 Exception 이 발생할 수 있음.
                   // 화면 회전 시에 새로운 fragment 를 띄우기를 원하는 것이 아니 므로 해당 에러는 무시함.
                   return
               }
            }
        }
    }

    internal fun getBanner(): PopupBannerPolicyItem {
        willShowPopupBannerPolicy.poll()?.let { popupBanner ->
            return popupBanner
        }
        throw BannerPolicyException.InvalidState("WillShowPopupBannerPolicy is Empty")
    }

    internal fun saveLocalBannerPolicy(id: String, notShowedDate: Date) {
        scope.launch {
            localBannerPolicy[id] = notShowedDate.time.toString()

            localBannerPolicySetter?.let { it(localBannerPolicy) }
        }
    }
}