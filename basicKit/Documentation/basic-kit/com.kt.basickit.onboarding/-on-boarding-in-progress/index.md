//[basicKit](../../../index.md)/[com.kt.basickit.onboarding](../index.md)/[OnBoardingInProgress](index.md)

# OnBoardingInProgress

[androidJvm]\
data class [OnBoardingInProgress](index.md)(steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[OnBoardingRoute](../-on-boarding-route/index.md)&gt;, currentStep: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [OnBoardingState](../-on-boarding-state/index.md)

## Properties

| Name | Summary |
|---|---|
| [currentStep](current-step.md) | [androidJvm]<br>open override val [currentStep](current-step.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isCancel](../-on-boarding-state/is-cancel.md) | [androidJvm]<br>open val [isCancel](../-on-boarding-state/is-cancel.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [nextRoute](../-on-boarding-state/next-route.md) | [androidJvm]<br>open val [nextRoute](../-on-boarding-state/next-route.md): [OnBoardingRoute](../-on-boarding-route/index.md) |
| [steps](steps.md) | [androidJvm]<br>open override val [steps](steps.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[OnBoardingRoute](../-on-boarding-route/index.md)&gt; |
| [totalStepSize](../-on-boarding-state/total-step-size.md) | [androidJvm]<br>open val [totalStepSize](../-on-boarding-state/total-step-size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
