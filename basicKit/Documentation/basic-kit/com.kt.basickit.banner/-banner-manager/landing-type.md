//[basicKit](../../../index.md)/[com.kt.basickit.banner](../index.md)/[BannerManager](index.md)/[landingType](landing-type.md)

# landingType

[androidJvm]\
val [landingType](landing-type.md): SharedFlow&lt;[BannerLandingType](../../com.kt.basickit.banner.domain.entity/-banner-landing-type/index.md)&gt;

DefaultBanner or PopupBanner 에 landingType 이 지정 되어 있을 경우, user 가 해당 Banner 을 click 하였을 때마다 해당 [BannerLandingType](../../com.kt.basickit.banner.domain.entity/-banner-landing-type/index.md) 을 publish.

Landing 하는 기능을 제공 하지 않으 므로, [landingType](landing-type.md) 을 collect 하여 직접 처리해야 함.
