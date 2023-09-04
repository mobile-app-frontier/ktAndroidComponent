//[basicKit](../../../index.md)/[com.kt.basickit.banner.data.source](../index.md)/[BannerPolicyDataSource](index.md)

# BannerPolicyDataSource

[androidJvm]\
interface [BannerPolicyDataSource](index.md)

BannerPolicy 를 서버 에서 받아올 때 사용 되는 DataSource Interface

## Functions

| Name | Summary |
|---|---|
| [getBannerPolicy](get-banner-policy.md) | [androidJvm]<br>abstract suspend fun [getBannerPolicy](get-banner-policy.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[BannerPolicyItemModel](../../com.kt.basickit.banner.data.model/-banner-policy-item-model/index.md)&gt;<br>서버 에서 BannerPolicy Json 정보를 받아와 List<BannerPolicyItemModel> 로 변환 하여 반환 하는 로직 |
