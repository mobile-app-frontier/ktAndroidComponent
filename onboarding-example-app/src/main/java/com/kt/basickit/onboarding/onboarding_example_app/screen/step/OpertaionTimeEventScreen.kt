package com.kt.basickit.onboarding.onboarding_example_app.screen.step

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.onboarding.navigator.AppNavigationRoute
import com.example.onboarding.navigator.onboarding.LocalNavigationProvider
import com.kt.basickit.onboarding.OnBoardingComplete
import com.kt.basickit.onboarding.OnBoardingViewModel
import com.kt.basickit.onboarding.onboarding_example_app.navigator.onboarding.AppOnBoardingRoute

@Composable
fun OperationTimeEventScreen(
    onBoardingNavController: NavHostController,
    onBoardingController: OnBoardingViewModel
) {
    val onBoardingState by onBoardingController.controllerState.collectAsStateWithLifecycle()
    val appNavigationController = LocalNavigationProvider.current

    val isOnBoardingComplete by remember(onBoardingState) {
        derivedStateOf { onBoardingState is OnBoardingComplete }
    }

    LaunchedEffect(isOnBoardingComplete) {
        if(isOnBoardingComplete) {
            Log.d("OnBoarding", "complete")
            appNavigationController.navigate(AppNavigationRoute.MAIN.routeName) {
                appNavigationController.popBackStack(appNavigationController.graph.startDestinationId, true)
            }
        }
    }

    BackHandler {
        onBoardingController.prev()
    }

    Column {
        Text("event!!! ${onBoardingState.currentStep}")
        Button(onClick = {
            onBoardingController.next()
        }) {
            Text("클릭")
        }
    }
}
