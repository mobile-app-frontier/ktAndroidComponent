//[basicKit](../../../index.md)/[com.kt.basickit.banner](../index.md)/[BannerManager](index.md)/[getDefaultBannerFragment](get-default-banner-fragment.md)

# getDefaultBannerFragment

[androidJvm]\
fun [getDefaultBannerFragment](get-default-banner-fragment.md)(category: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html)

category 에 해당 하는 Default Banner 들의 ImageSlider UI Fragment 를 제공.

#### Return

ImageSlider UI Fragment

## Parameters

androidJvm

| | |
|---|---|
| category | default banner 의 category |

## Throws

| | |
|---|---|
| [com.kt.basickit.banner.exception.BannerPolicyException.InvalidState](../../com.kt.basickit.banner.exception/-banner-policy-exception/-invalid-state/index.md) | BannerPolicyFetcher fetch 성공 전에 BannerManager 사용시 발생. |
