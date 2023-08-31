package com.kt.basickit.onboarding.onboarding_xml_test_app.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kt.basickit.onboarding.onboarding_xml_test_app.navigator.onboarding.LocalNavigationProvider
import com.kt.basickit.onboarding.OnBoardingScreen
import com.kt.basickit.onboarding.onboarding_xml_test_app.screen.MainScreen
import com.kt.basickit.onboarding.onboarding_xml_test_app.screen.RootScreen
import com.kt.basickit.onboarding.onboarding_xml_test_app.databinding.OnboardingPageOneBinding
import com.kt.basickit.onboarding.onboarding_xml_test_app.databinding.OnboardingPageTwoBinding
import com.kt.basickit.onboarding.onboarding_xml_test_app.navigator.onboarding.OnBoardingPageOne
import com.kt.basickit.onboarding.onboarding_xml_test_app.navigator.onboarding.OnBoardingPageTwo

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
                OnBoardingScreen(
                    steps = listOf(
                        OnBoardingPageOne(
                            binding = OnboardingPageOneBinding::inflate,
                            updateBiding = { viewBinding, onBoardingViewModel ->
                                viewBinding.onboardingViewModel = onBoardingViewModel
                            }
                        ),
                        OnBoardingPageTwo(
                            binding = OnboardingPageTwoBinding::inflate,
                            updateBiding = { viewBinding, onBoardingViewModel ->
                                viewBinding.onboardingViewModel = onBoardingViewModel
                            }
                        ),
                    ),
                )
            }
            composable(route = AppNavigationRoute.MAIN.routeName) {
                MainScreen()
            }
        }
    }
}
