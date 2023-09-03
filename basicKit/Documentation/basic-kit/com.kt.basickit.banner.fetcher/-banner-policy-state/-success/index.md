//[basicKit](../../../../index.md)/[com.kt.basickit.banner.fetcher](../../index.md)/[BannerPolicyState](../index.md)/[Success](index.md)

# Success

[androidJvm]\
data class [Success](index.md)(willShowBanner: [BannerPolicy](../../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md)) : [BannerPolicyState](../index.md)

보여줄 remote banner policy 와 보여 주지 않을 local banner policy 를 비교 하여 보여줄 정책을 filtering 완료한 상태

## Constructors

| | |
|---|---|
| [Success](-success.md) | [androidJvm]<br>fun [Success](-success.md)(willShowBanner: [BannerPolicy](../../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [willShowBanner](../will-show-banner.md) | [androidJvm]<br>fun [willShowBanner](../will-show-banner.md)(): [BannerPolicy](../../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md)?<br>보여줄 배너 정책 |

## Properties

| Name | Summary |
|---|---|
| [willShowBanner](will-show-banner.md) | [androidJvm]<br>val [willShowBanner](will-show-banner.md): [BannerPolicy](../../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md)<br>실제 보여줄 배너 정책 |
