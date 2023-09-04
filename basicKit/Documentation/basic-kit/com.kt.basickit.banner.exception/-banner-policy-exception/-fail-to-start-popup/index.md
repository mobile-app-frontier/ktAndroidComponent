//[basicKit](../../../../index.md)/[com.kt.basickit.banner.exception](../../index.md)/[BannerPolicyException](../index.md)/[FailToStartPopup](index.md)

# FailToStartPopup

[androidJvm]\
class [FailToStartPopup](index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [BannerPolicyException](../index.md)

PopupBanner start 에 실패 ex) FragmentActivity 가 아닌 Activity 에서 BannerManager 를 사용 했을 경우

## Parameters

androidJvm

| | |
|---|---|
| message | error message(reason) |

## Constructors

| | |
|---|---|
| [FailToStartPopup](-fail-to-start-popup.md) | [androidJvm]<br>fun [FailToStartPopup](-fail-to-start-popup.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>error message 를 지닌 [BannerPolicyException](../index.md) 를 create. |

## Functions

| Name | Summary |
|---|---|
| [addSuppressed](index.md#282858770%2FFunctions%2F2043513891) | [androidJvm]<br>fun [addSuppressed](index.md#282858770%2FFunctions%2F2043513891)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [fillInStackTrace](index.md#-1102069925%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [fillInStackTrace](index.md#-1102069925%2FFunctions%2F2043513891)(): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [getLocalizedMessage](index.md#1043865560%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [getLocalizedMessage](index.md#1043865560%2FFunctions%2F2043513891)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getStackTrace](index.md#2050903719%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [getStackTrace](index.md#2050903719%2FFunctions%2F2043513891)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt; |
| [getSuppressed](index.md#672492560%2FFunctions%2F2043513891) | [androidJvm]<br>fun [getSuppressed](index.md#672492560%2FFunctions%2F2043513891)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)&gt; |
| [initCause](index.md#-418225042%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [initCause](index.md#-418225042%2FFunctions%2F2043513891)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [printStackTrace](index.md#-1769529168%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [printStackTrace](index.md#-1769529168%2FFunctions%2F2043513891)()<br>open fun [printStackTrace](index.md#1841853697%2FFunctions%2F2043513891)(p0: [PrintStream](https://developer.android.com/reference/kotlin/java/io/PrintStream.html))<br>open fun [printStackTrace](index.md#1175535278%2FFunctions%2F2043513891)(p0: [PrintWriter](https://developer.android.com/reference/kotlin/java/io/PrintWriter.html)) |
| [setStackTrace](index.md#2135801318%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [setStackTrace](index.md#2135801318%2FFunctions%2F2043513891)(p0: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [cause](index.md#-654012527%2FProperties%2F2043513891) | [androidJvm]<br>open val [cause](index.md#-654012527%2FProperties%2F2043513891): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? |
| [message](index.md#1824300659%2FProperties%2F2043513891) | [androidJvm]<br>open val [message](index.md#1824300659%2FProperties%2F2043513891): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
