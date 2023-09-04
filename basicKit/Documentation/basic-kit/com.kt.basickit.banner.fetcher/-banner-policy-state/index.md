//[basicKit](../../../index.md)/[com.kt.basickit.banner.fetcher](../index.md)/[BannerPolicyState](index.md)

# BannerPolicyState

[androidJvm]\
sealed class [BannerPolicyState](index.md)

[BannerPolicyFetcher](../-banner-policy-fetcher/index.md) 의 상태

- 
   [Initial](-initial/index.md) 초기 상태
- 
   [Fetched](-fetched/index.md) remote banner policy 와 local banner policy 를 읽어온 상태.
- 
   [Success](-success/index.md) 읽어온 remote & local banner policy 를 비교 하여 보여줄 banner 를 filtering 완료한 상태.
- 
   [Fail](-fail/index.md) fetch 과정 중, [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) 이 발생 하여 실패한 상태.

## Types

| Name | Summary |
|---|---|
| [Fail](-fail/index.md) | [androidJvm]<br>data class [Fail](-fail/index.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) : [BannerPolicyState](index.md)<br>remote banner policy or local banner policy fetch 에 실패한 상태 |
| [Fetched](-fetched/index.md) | [androidJvm]<br>data class [Fetched](-fetched/index.md)(remoteBanner: [BannerPolicy](../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md), localBanner: [LocalBannerPolicy](../../com.kt.basickit.banner/index.md#-2125375971%2FClasslikes%2F2043513891)) : [BannerPolicyState](index.md)<br>remote banner policy 와 local banner policy 정보를 받아온 상태. |
| [Initial](-initial/index.md) | [androidJvm]<br>object [Initial](-initial/index.md) : [BannerPolicyState](index.md)<br>초기 상태 |
| [Success](-success/index.md) | [androidJvm]<br>data class [Success](-success/index.md)(willShowBanner: [BannerPolicy](../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md)) : [BannerPolicyState](index.md)<br>보여줄 remote banner policy 와 보여 주지 않을 local banner policy 를 비교 하여 보여줄 정책을 filtering 완료한 상태 |

## Functions

| Name | Summary |
|---|---|
| [willShowBanner](will-show-banner.md) | [androidJvm]<br>fun [willShowBanner](will-show-banner.md)(): [BannerPolicy](../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md)?<br>보여줄 배너 정책 |

## Inheritors

| Name |
|---|
| [Initial](-initial/index.md) |
| [Fetched](-fetched/index.md) |
| [Success](-success/index.md) |
| [Fail](-fail/index.md) |
