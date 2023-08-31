package com.kt.basickit.onboarding.onboarding_example_app.navigator.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.ComposeOnBoardingRoute
import com.kt.basickit.onboarding.OnBoardingViewModel
import com.kt.basickit.onboarding.onboarding_example_app.screen.step.OnBoardingPageOneScreen
import com.kt.basickit.onboarding.onboarding_example_app.screen.step.OnBoardingPageThreeScreen
import com.kt.basickit.onboarding.onboarding_example_app.screen.step.OnBoardingPageTwoScreen

enum class AppOnBoardingRoute(
    override val routeName: String,
    override val screen: @Composable (NavHostController, OnBoardingViewModel) -> Unit,
) : ComposeOnBoardingRoute {
    OnBoardingPageOne(
        "/onBoardingPageOne",
        screen = { onBoardingNavController, onBoardingController -> OnBoardingPageOneScreen(onBoardingNavController, onBoardingController) },
    ),
    OnBoardingPageTwo(
        "/onBoardingPageTwo",
        screen = { onBoardingNavController, onBoardingController -> OnBoardingPageTwoScreen(onBoardingNavController, onBoardingController) },
    ),
    OnBoardingPageThree(
        "/onBoardingPageThree",
        screen = { onBoardingNavController, onBoardingController ->
            OnBoardingPageThreeScreen(onBoardingNavController, onBoardingController)
        },
    )
    ;
}
