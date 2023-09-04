//[basicKit](../../../index.md)/[com.kt.basickit.banner.exception](../index.md)/[BannerPolicyException](index.md)

# BannerPolicyException

[androidJvm]\
sealed class [BannerPolicyException](index.md) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)

Banner 내부 로직에 의해 발생할 수 있는 Exception

## Parameters

androidJvm

| | |
|---|---|
| message | error message(reason) |

## Types

| Name | Summary |
|---|---|
| [FailToStartPopup](-fail-to-start-popup/index.md) | [androidJvm]<br>class [FailToStartPopup](-fail-to-start-popup/index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [BannerPolicyException](index.md)<br>PopupBanner start 에 실패 ex) FragmentActivity 가 아닌 Activity 에서 BannerManager 를 사용 했을 경우 |
| [InvalidState](-invalid-state/index.md) | [androidJvm]<br>class [InvalidState](-invalid-state/index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [BannerPolicyException](index.md)<br>Invalid State ex) BannerManager 를 initialize 전에 사용 했을 경우 |

## Functions

| Name | Summary |
|---|---|
| [addSuppressed](-fail-to-start-popup/index.md#282858770%2FFunctions%2F2043513891) | [androidJvm]<br>fun [addSuppressed](-fail-to-start-popup/index.md#282858770%2FFunctions%2F2043513891)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [fillInStackTrace](-fail-to-start-popup/index.md#-1102069925%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [fillInStackTrace](-fail-to-start-popup/index.md#-1102069925%2FFunctions%2F2043513891)(): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [getLocalizedMessage](-fail-to-start-popup/index.md#1043865560%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [getLocalizedMessage](-fail-to-start-popup/index.md#1043865560%2FFunctions%2F2043513891)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getStackTrace](-fail-to-start-popup/index.md#2050903719%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [getStackTrace](-fail-to-start-popup/index.md#2050903719%2FFunctions%2F2043513891)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt; |
| [getSuppressed](-fail-to-start-popup/index.md#672492560%2FFunctions%2F2043513891) | [androidJvm]<br>fun [getSuppressed](-fail-to-start-popup/index.md#672492560%2FFunctions%2F2043513891)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)&gt; |
| [initCause](-fail-to-start-popup/index.md#-418225042%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [initCause](-fail-to-start-popup/index.md#-418225042%2FFunctions%2F2043513891)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [printStackTrace](-fail-to-start-popup/index.md#-1769529168%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [printStackTrace](-fail-to-start-popup/index.md#-1769529168%2FFunctions%2F2043513891)()<br>open fun [printStackTrace](-fail-to-start-popup/index.md#1841853697%2FFunctions%2F2043513891)(p0: [PrintStream](https://developer.android.com/reference/kotlin/java/io/PrintStream.html))<br>open fun [printStackTrace](-fail-to-start-popup/index.md#1175535278%2FFunctions%2F2043513891)(p0: [PrintWriter](https://developer.android.com/reference/kotlin/java/io/PrintWriter.html)) |
| [setStackTrace](-fail-to-start-popup/index.md#2135801318%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [setStackTrace](-fail-to-start-popup/index.md#2135801318%2FFunctions%2F2043513891)(p0: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [cause](-fail-to-start-popup/index.md#-654012527%2FProperties%2F2043513891) | [androidJvm]<br>open val [cause](-fail-to-start-popup/index.md#-654012527%2FProperties%2F2043513891): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? |
| [message](-fail-to-start-popup/index.md#1824300659%2FProperties%2F2043513891) | [androidJvm]<br>open val [message](-fail-to-start-popup/index.md#1824300659%2FProperties%2F2043513891): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |

## Inheritors

| Name |
|---|
| [InvalidState](-invalid-state/index.md) |
| [FailToStartPopup](-fail-to-start-popup/index.md) |
