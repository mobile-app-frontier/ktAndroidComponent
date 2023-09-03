//[basicKit](../../../../index.md)/[com.kt.basickit.banner.fetcher](../../index.md)/[BannerPolicyState](../index.md)/[Fetched](index.md)

# Fetched

[androidJvm]\
data class [Fetched](index.md)(remoteBanner: [BannerPolicy](../../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md), localBanner: [LocalBannerPolicy](../../../com.kt.basickit.banner/index.md#-2125375971%2FClasslikes%2F2043513891)) : [BannerPolicyState](../index.md)

remote banner policy 와 local banner policy 정보를 받아온 상태.

## Constructors

| | |
|---|---|
| [Fetched](-fetched.md) | [androidJvm]<br>fun [Fetched](-fetched.md)(remoteBanner: [BannerPolicy](../../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md), localBanner: [LocalBannerPolicy](../../../com.kt.basickit.banner/index.md#-2125375971%2FClasslikes%2F2043513891)) |

## Functions

| Name | Summary |
|---|---|
| [willShowBanner](../will-show-banner.md) | [androidJvm]<br>fun [willShowBanner](../will-show-banner.md)(): [BannerPolicy](../../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md)?<br>보여줄 배너 정책 |

## Properties

| Name | Summary |
|---|---|
| [localBanner](local-banner.md) | [androidJvm]<br>val [localBanner](local-banner.md): [LocalBannerPolicy](../../../com.kt.basickit.banner/index.md#-2125375971%2FClasslikes%2F2043513891)<br>단말 에서 읽어온 보여 주지 않을 배너 정책. |
| [remoteBanner](remote-banner.md) | [androidJvm]<br>val [remoteBanner](remote-banner.md): [BannerPolicy](../../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md)<br>서버 에서 내려준 보여줄 배너 정책. |
