//[basicKit](../../index.md)/[com.kt.basickit.onboarding](index.md)

# Package com.kt.basickit.onboarding

## Types

| Name | Summary |
|---|---|
| [OnBoardingCancel](-on-boarding-cancel/index.md) | [androidJvm]<br>data class [OnBoardingCancel](-on-boarding-cancel/index.md)(steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[OnBoardingRoute](-on-boarding-route/index.md)&gt;, currentStep: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [OnBoardingState](-on-boarding-state/index.md) |
| [OnBoardingComplete](-on-boarding-complete/index.md) | [androidJvm]<br>data class [OnBoardingComplete](-on-boarding-complete/index.md)(steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[OnBoardingRoute](-on-boarding-route/index.md)&gt;, currentStep: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [OnBoardingState](-on-boarding-state/index.md) |
| [OnBoardingInProgress](-on-boarding-in-progress/index.md) | [androidJvm]<br>data class [OnBoardingInProgress](-on-boarding-in-progress/index.md)(steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[OnBoardingRoute](-on-boarding-route/index.md)&gt;, currentStep: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [OnBoardingState](-on-boarding-state/index.md) |
| [OnBoardingRoute](-on-boarding-route/index.md) | [androidJvm]<br>interface [OnBoardingRoute](-on-boarding-route/index.md) |
| [OnBoardingState](-on-boarding-state/index.md) | [androidJvm]<br>interface [OnBoardingState](-on-boarding-state/index.md) |
| [OnBoardingViewModel](-on-boarding-view-model/index.md) | [androidJvm]<br>interface [OnBoardingViewModel](-on-boarding-view-model/index.md) |

## Functions

| Name | Summary |
|---|---|
| [OnBoardingScreen](-on-boarding-screen.md) | [androidJvm]<br>@Composable<br>fun &lt;[CustomStep](-on-boarding-screen.md) : [OnBoardingRoute](-on-boarding-route/index.md)&gt; [OnBoardingScreen](-on-boarding-screen.md)(steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[CustomStep](-on-boarding-screen.md)&gt;, currentStep: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, onBoardingTopArea: @Composable([NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html), [OnBoardingViewModel](-on-boarding-view-model/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onBoardingBottomArea: @Composable([NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html), [OnBoardingViewModel](-on-boarding-view-model/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) |
