package com.kt.basickit.onboarding.onboarding_example_app.navigator.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.OnBoardingRoute
import com.kt.basickit.onboarding.OnBoardingViewModel
import com.kt.basickit.onboarding.onboarding_example_app.screen.step.OperationTimeAndHolidayScreen
import com.kt.basickit.onboarding.onboarding_example_app.screen.step.OperationTimeEventScreen
import com.kt.basickit.onboarding.onboarding_example_app.screen.step.OperationTimeScreen

enum class AppOnBoardingRoute(
    override val routeName: String,
    override val screen: @Composable (NavHostController, OnBoardingViewModel) -> Unit,
) : OnBoardingRoute {
    OperationTimeAndHoliday(
        "/operationTimeAndHoliday",
        screen = { onBoardingNavController, onBoardingController -> OperationTimeAndHolidayScreen(onBoardingNavController, onBoardingController) },
    ),
    OperationTime(
        "/operationTimeAndHoliday/time",
        screen = { onBoardingNavController, onBoardingController -> OperationTimeScreen(onBoardingNavController, onBoardingController) },
    ),
    OperationTimeEvent(
        "/operationTimeAndHoliday/event",
        screen = { onBoardingNavController, onBoardingController ->
            OperationTimeEventScreen(onBoardingNavController, onBoardingController)
        },
    )
    ;
}
