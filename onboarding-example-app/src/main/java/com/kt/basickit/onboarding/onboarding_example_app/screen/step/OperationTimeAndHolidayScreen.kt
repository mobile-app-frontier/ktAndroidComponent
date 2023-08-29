package com.kt.basickit.onboarding.onboarding_example_app.screen.step

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.OnBoardingCancel
import com.kt.basickit.onboarding.OnBoardingViewModel

@Composable
fun OperationTimeAndHolidayScreen(
    onBoardingNavController: NavHostController,
    onBoardingController: OnBoardingViewModel,
) {
    val onBoardingControllerState =
        onBoardingController.controllerState.collectAsStateWithLifecycle()
    val context = LocalContext.current as Activity

    if (onBoardingControllerState.value is OnBoardingCancel) {
        AlertDialog(onDismissRequest = {}, confirmButton = {
            Button(onClick = {
                context.finish()
            }) {
                Text("종료")
            }
        })
    }

    BackHandler {
        onBoardingController.prev()
    }

    Column {
        Text("time and holiday: ${onBoardingControllerState.value.currentStep}")
        Button(onClick = {
            onBoardingController.next()
        }) {
            Text("time")
        }
    }

}
