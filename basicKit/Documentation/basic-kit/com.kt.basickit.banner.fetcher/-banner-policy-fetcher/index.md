//[basicKit](../../../index.md)/[com.kt.basickit.banner.fetcher](../index.md)/[BannerPolicyFetcher](index.md)

# BannerPolicyFetcher

[androidJvm]\
class [BannerPolicyFetcher](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), dataSource: [BannerPolicyDataSource](../../com.kt.basickit.banner.data.source/-banner-policy-data-source/index.md), localBannerPolicyGetter: suspend () -&gt; [LocalBannerPolicy](../../com.kt.basickit.banner/index.md#-2125375971%2FClasslikes%2F2043513891), localBannerPolicySetter: suspend ([LocalBannerPolicy](../../com.kt.basickit.banner/index.md#-2125375971%2FClasslikes%2F2043513891)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), appVersion: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

서버 에서 받아온 배너 정책과 단말에 저장된 정책(보여 주지 않을 배너에 대한 정보) 을 가져와 비교 하여 보여줄 배너를 보여줌.

## Parameters

androidJvm

| | |
|---|---|
| dataSource |  |
| appVersion |  |

## Constructors

| | |
|---|---|
| [BannerPolicyFetcher](-banner-policy-fetcher.md) | [androidJvm]<br>fun [BannerPolicyFetcher](-banner-policy-fetcher.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), dataSource: [BannerPolicyDataSource](../../com.kt.basickit.banner.data.source/-banner-policy-data-source/index.md), localBannerPolicyGetter: suspend () -&gt; [LocalBannerPolicy](../../com.kt.basickit.banner/index.md#-2125375971%2FClasslikes%2F2043513891), localBannerPolicySetter: suspend ([LocalBannerPolicy](../../com.kt.basickit.banner/index.md#-2125375971%2FClasslikes%2F2043513891)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), appVersion: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>crete BannerPolicyFetcher |

## Functions

| Name | Summary |
|---|---|
| [fetch](fetch.md) | [androidJvm]<br>suspend fun [fetch](fetch.md)()<br>remoteBannerPolicy 와 localBannerPolicy 를 동시에 fetch 하고 비교 하여 보여줄 배너를 filtering 함. |

## Properties

| Name | Summary |
|---|---|
| [state](state.md) | [androidJvm]<br>val [state](state.md): StateFlow&lt;[BannerPolicyState](../-banner-policy-state/index.md)&gt;<br>[BannerPolicyFetcher](index.md) 의 현재 상태. |
