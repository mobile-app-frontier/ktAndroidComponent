//[basicKit](../../../../index.md)/[com.kt.basickit.banner.exception](../../index.md)/[BannerPolicyException](../index.md)/[InvalidState](index.md)

# InvalidState

[androidJvm]\
class [InvalidState](index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [BannerPolicyException](../index.md)

Invalid State ex) BannerManager 를 initialize 전에 사용 했을 경우

## Parameters

androidJvm

| | |
|---|---|
| message | error message(reason) |

## Constructors

| | |
|---|---|
| [InvalidState](-invalid-state.md) | [androidJvm]<br>fun [InvalidState](-invalid-state.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>error message 를 지닌 [BannerPolicyException](../index.md) 를 create. |

## Functions

| Name | Summary |
|---|---|
| [addSuppressed](../-fail-to-start-popup/index.md#282858770%2FFunctions%2F2043513891) | [androidJvm]<br>fun [addSuppressed](../-fail-to-start-popup/index.md#282858770%2FFunctions%2F2043513891)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [fillInStackTrace](../-fail-to-start-popup/index.md#-1102069925%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [fillInStackTrace](../-fail-to-start-popup/index.md#-1102069925%2FFunctions%2F2043513891)(): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [getLocalizedMessage](../-fail-to-start-popup/index.md#1043865560%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [getLocalizedMessage](../-fail-to-start-popup/index.md#1043865560%2FFunctions%2F2043513891)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getStackTrace](../-fail-to-start-popup/index.md#2050903719%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [getStackTrace](../-fail-to-start-popup/index.md#2050903719%2FFunctions%2F2043513891)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt; |
| [getSuppressed](../-fail-to-start-popup/index.md#672492560%2FFunctions%2F2043513891) | [androidJvm]<br>fun [getSuppressed](../-fail-to-start-popup/index.md#672492560%2FFunctions%2F2043513891)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)&gt; |
| [initCause](../-fail-to-start-popup/index.md#-418225042%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [initCause](../-fail-to-start-popup/index.md#-418225042%2FFunctions%2F2043513891)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [printStackTrace](../-fail-to-start-popup/index.md#-1769529168%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [printStackTrace](../-fail-to-start-popup/index.md#-1769529168%2FFunctions%2F2043513891)()<br>open fun [printStackTrace](../-fail-to-start-popup/index.md#1841853697%2FFunctions%2F2043513891)(p0: [PrintStream](https://developer.android.com/reference/kotlin/java/io/PrintStream.html))<br>open fun [printStackTrace](../-fail-to-start-popup/index.md#1175535278%2FFunctions%2F2043513891)(p0: [PrintWriter](https://developer.android.com/reference/kotlin/java/io/PrintWriter.html)) |
| [setStackTrace](../-fail-to-start-popup/index.md#2135801318%2FFunctions%2F2043513891) | [androidJvm]<br>open fun [setStackTrace](../-fail-to-start-popup/index.md#2135801318%2FFunctions%2F2043513891)(p0: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [cause](../-fail-to-start-popup/index.md#-654012527%2FProperties%2F2043513891) | [androidJvm]<br>open val [cause](../-fail-to-start-popup/index.md#-654012527%2FProperties%2F2043513891): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? |
| [message](../-fail-to-start-popup/index.md#1824300659%2FProperties%2F2043513891) | [androidJvm]<br>open val [message](../-fail-to-start-popup/index.md#1824300659%2FProperties%2F2043513891): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
