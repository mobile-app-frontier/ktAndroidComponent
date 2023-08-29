package com.kt.basickit.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface OnBoardingRoute {
    val routeName: String
    val screen: @Composable (NavHostController, OnBoardingViewModel) -> Unit
}
