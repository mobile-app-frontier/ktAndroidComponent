//[basicKit](../../index.md)/[com.kt.basickit.banner.fetcher](index.md)

# Package com.kt.basickit.banner.fetcher

## Types

| Name | Summary |
|---|---|
| [BannerPolicyFetcher](-banner-policy-fetcher/index.md) | [androidJvm]<br>class [BannerPolicyFetcher](-banner-policy-fetcher/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), dataSource: [BannerPolicyDataSource](../com.kt.basickit.banner.data.source/-banner-policy-data-source/index.md), localBannerPolicyGetter: suspend () -&gt; [LocalBannerPolicy](../com.kt.basickit.banner/index.md#-2125375971%2FClasslikes%2F2043513891), localBannerPolicySetter: suspend ([LocalBannerPolicy](../com.kt.basickit.banner/index.md#-2125375971%2FClasslikes%2F2043513891)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), appVersion: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>서버 에서 받아온 배너 정책과 단말에 저장된 정책(보여 주지 않을 배너에 대한 정보) 을 가져와 비교 하여 보여줄 배너를 보여줌. |
| [BannerPolicyState](-banner-policy-state/index.md) | [androidJvm]<br>sealed class [BannerPolicyState](-banner-policy-state/index.md)<br>[BannerPolicyFetcher](-banner-policy-fetcher/index.md) 의 상태 |
