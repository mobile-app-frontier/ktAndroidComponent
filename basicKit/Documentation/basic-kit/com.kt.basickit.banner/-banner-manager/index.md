//[basicKit](../../../index.md)/[com.kt.basickit.banner](../index.md)/[BannerManager](index.md)

# BannerManager

[androidJvm]\
object [BannerManager](index.md)

PopupBanner Bottom Sheet 를 show/hide 관리와 category 별 DefaultBanner Fragment 를 제공 해주는 역할을 함.

<em>BannerPolicyFetcher 를 통해 fetch() 를 완료한 후, BannerPolicyFetcher 의 state 가 Success 일 때만 사용 가능.</em><em>PopupBanner 의 경우, FragmentActivity 에서만 사용 가능.</em>

## Functions

| Name | Summary |
|---|---|
| [DefaultBannerView](-default-banner-view.md) | [androidJvm]<br>@Composable<br>fun [DefaultBannerView](-default-banner-view.md)(category: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), modifier: Modifier = Modifier)<br>category 에 해당 하는 DefaultBannerView(Image Slider) Composable UI 를 제공 |
| [getDefaultBannerFragment](get-default-banner-fragment.md) | [androidJvm]<br>fun [getDefaultBannerFragment](get-default-banner-fragment.md)(category: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html)<br>category 에 해당 하는 Default Banner 들의 ImageSlider UI Fragment 를 제공. |
| [startPopupBanner](start-popup-banner.md) | [androidJvm]<br>fun [startPopupBanner](start-popup-banner.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), buttonTextStyle: TextStyle? = null, popupBannerTextStyle: TextStyle? = null)<br>start PopupBanner bottomSheet show |

## Properties

| Name | Summary |
|---|---|
| [landingType](landing-type.md) | [androidJvm]<br>val [landingType](landing-type.md): SharedFlow&lt;[BannerLandingType](../../com.kt.basickit.banner.domain.entity/-banner-landing-type/index.md)&gt;<br>DefaultBanner or PopupBanner 에 landingType 이 지정 되어 있을 경우, user 가 해당 Banner 을 click 하였을 때마다 해당 [BannerLandingType](../../com.kt.basickit.banner.domain.entity/-banner-landing-type/index.md) 을 publish. |
