//[basicKit](../../../index.md)/[com.kt.basickit.banner](../index.md)/[BannerManager](index.md)/[startPopupBanner](start-popup-banner.md)

# startPopupBanner

[androidJvm]\
fun [startPopupBanner](start-popup-banner.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), buttonTextStyle: TextStyle? = null, popupBannerTextStyle: TextStyle? = null)

start PopupBanner bottomSheet show

## Parameters

androidJvm

| | |
|---|---|
| context |  |
| buttonTextStyle | PopupBanner Button 의 TextStyle. null 일 경우 [TextStyle.Companion.defaultButtonTextStyle](../../com.kt.basickit.banner.view.option/default-button-text-style.md) 를 사용. |
| popupBannerTextStyle | PopupBanner contentType 이 Text 일 때의 TextStyle. null 일 경우 [TextStyle.Companion.defaultBannerTextStyle](../../com.kt.basickit.banner.view.option/default-banner-text-style.md) 를 사용. |

## Throws

| | |
|---|---|
| [com.kt.basickit.banner.exception.BannerPolicyException.InvalidState](../../com.kt.basickit.banner.exception/-banner-policy-exception/-invalid-state/index.md) | BannerPolicyFetcher fetch 성공 전에 BannerManager 사용시 발생. |
| [com.kt.basickit.banner.exception.BannerPolicyException.FailToStartPopup](../../com.kt.basickit.banner.exception/-banner-policy-exception/-fail-to-start-popup/index.md) | Activity 가 FragmentActivity 가 아닐 경우 발생. |
