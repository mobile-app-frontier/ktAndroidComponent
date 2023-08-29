package com.kt.basickit.onboarding.onboarding_example_app.screen.step

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.OnBoardingCancel
import com.kt.basickit.onboarding.OnBoardingViewModel

@Composable
fun OnBoardingPageOneScreen(
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

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("page 1", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Button(onClick = {
            onBoardingController.next()
        }) {
            Text("next", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }

}
