//[basicKit](../../../index.md)/[com.kt.basickit.onboarding](../index.md)/[OnBoardingState](index.md)

# OnBoardingState

[androidJvm]\
interface [OnBoardingState](index.md)

## Properties

| Name | Summary |
|---|---|
| [currentStep](current-step.md) | [androidJvm]<br>abstract val [currentStep](current-step.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isCancel](is-cancel.md) | [androidJvm]<br>open val [isCancel](is-cancel.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [nextRoute](next-route.md) | [androidJvm]<br>open val [nextRoute](next-route.md): [OnBoardingRoute](../-on-boarding-route/index.md) |
| [steps](steps.md) | [androidJvm]<br>abstract val [steps](steps.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[OnBoardingRoute](../-on-boarding-route/index.md)&gt; |
| [totalStepSize](total-step-size.md) | [androidJvm]<br>open val [totalStepSize](total-step-size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

## Inheritors

| Name |
|---|
| [OnBoardingCancel](../-on-boarding-cancel/index.md) |
| [OnBoardingInProgress](../-on-boarding-in-progress/index.md) |
| [OnBoardingComplete](../-on-boarding-complete/index.md) |
