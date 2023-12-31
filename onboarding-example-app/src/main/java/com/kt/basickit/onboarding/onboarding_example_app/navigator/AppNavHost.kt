package com.kt.basickit.onboarding.onboarding_example_app.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kt.basickit.onboarding.route.ComposeOnBoardingRoute
import com.kt.basickit.onboarding.onboarding_example_app.navigator.onboarding.AppOnBoardingRoute
import com.kt.basickit.onboarding.onboarding_example_app.navigator.onboarding.LocalNavigationProvider
import com.kt.basickit.onboarding.OnBoardingScreen
import com.kt.basickit.onboarding.OnBoardingViewModel
import com.kt.basickit.onboarding.onboarding_example_app.screen.MainScreen
import com.kt.basickit.onboarding.onboarding_example_app.screen.RootScreen
import com.kt.basickit.onboarding.onboarding_example_app.screen.step.view.SampleTopView
import com.kt.basickit.onboarding.view.ComposeOnBoardingVerticalSideArea

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    CompositionLocalProvider(LocalNavigationProvider provides navController) {
        NavHost(
            navController = navController,
            startDestination = AppNavigationRoute.ROOT.routeName,
        ) {
            composable(route = AppNavigationRoute.ROOT.routeName) {
                RootScreen()
            }
            composable(route = AppNavigationRoute.ONBOARIDNG.routeName) {
                OnBoardingScreen<ComposeOnBoardingRoute, ComposeOnBoardingVerticalSideArea>(
                    steps = AppOnBoardingRoute.values().toList(),
                    onBoardingTopArea = object: ComposeOnBoardingVerticalSideArea {
                        @Composable
                        override fun Compose(
                            navHostController: NavHostController,
                            onBoardingViewModel: OnBoardingViewModel
                        ) {
                            SampleTopView(navHostController, onBoardingViewModel)
                        }

                    }
                )
            }
            composable(route = AppNavigationRoute.MAIN.routeName) {
                MainScreen()
            }
        }
    }
}
