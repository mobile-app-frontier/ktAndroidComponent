package com.kt.basickit.onboarding.onboarding_example_app.screen.step

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.onboarding_example_app.navigator.AppNavigationRoute
import com.kt.basickit.onboarding.onboarding_example_app.navigator.onboarding.LocalNavigationProvider
import com.kt.basickit.onboarding.OnBoardingComplete
import com.kt.basickit.onboarding.OnBoardingViewModel

@Composable
fun OnBoardingPageThreeScreen(
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

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("page 3", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Button(onClick = {
            onBoardingController.next()
        }) {
            Text("complete", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}
