package com.kt.basickit.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun <CustomStep : OnBoardingRoute> OnBoardingScreen(
    steps: List<CustomStep>,
    currentStep: Int = 0,
    onBoardingTopArea: (@Composable (NavHostController, OnBoardingViewModel) -> Unit)? = null,
    onBoardingBottomArea: (@Composable (NavHostController, OnBoardingViewModel) -> Unit)? = null,
) {
    val onBoardingNavController = rememberNavController()
    val onBoardingViewModel : OnBoardingViewModelImpl = remember {
        OnBoardingViewModelImpl(
            navController = onBoardingNavController,
            steps = steps,
            currentStep = currentStep,
        )
    }
    Column {
        onBoardingTopArea?.let {
            onBoardingTopArea(onBoardingNavController, onBoardingViewModel)
        }
        OnBoardingNavHost(onBoardingNavController = onBoardingNavController, onBoardingViewModel = onBoardingViewModel, steps = steps,)
        onBoardingBottomArea?.let {
            onBoardingBottomArea(onBoardingNavController, onBoardingViewModel)
        }
    }
}

@Composable
private fun <CustomStep : OnBoardingRoute> OnBoardingNavHost(
    onBoardingNavController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel,
    steps: List<CustomStep>,
) {
    NavHost(
        navController = onBoardingNavController,
        startDestination = steps.first().routeName,
    ) {
        steps.forEach { onBoardingRoute ->
            composable(
                route = onBoardingRoute.routeName
            ) {
                onBoardingRoute.screen(onBoardingNavController, onBoardingViewModel)
            }
        }
    }
}
