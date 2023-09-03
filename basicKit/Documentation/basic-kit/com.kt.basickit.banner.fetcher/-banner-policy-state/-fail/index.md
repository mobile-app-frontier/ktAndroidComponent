//[basicKit](../../../../index.md)/[com.kt.basickit.banner.fetcher](../../index.md)/[BannerPolicyState](../index.md)/[Fail](index.md)

# Fail

[androidJvm]\
data class [Fail](index.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) : [BannerPolicyState](../index.md)

remote banner policy or local banner policy fetch 에 실패한 상태

## Constructors

| | |
|---|---|
| [Fail](-fail.md) | [androidJvm]<br>fun [Fail](-fail.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [willShowBanner](../will-show-banner.md) | [androidJvm]<br>fun [willShowBanner](../will-show-banner.md)(): [BannerPolicy](../../../com.kt.basickit.banner.domain.entity/-banner-policy/index.md)?<br>보여줄 배너 정책 |

## Properties

| Name | Summary |
|---|---|
| [error](error.md) | [androidJvm]<br>val [error](error.md): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
