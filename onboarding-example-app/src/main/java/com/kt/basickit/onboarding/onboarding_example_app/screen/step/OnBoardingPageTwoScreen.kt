package com.kt.basickit.onboarding.onboarding_example_app.screen.step

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.OnBoardingViewModel
import com.kt.basickit.onboarding.onboarding_example_app.navigator.onboarding.AppOnBoardingRoute

@Composable
fun OnBoardingPageTwoScreen(
    onBoardingNavController: NavHostController,
    onBoardingController: OnBoardingViewModel,
) {
    BackHandler {
        onBoardingController.prev()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text("page 2", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Button(onClick = {
            onBoardingController.next()
        }) {
            Text("다음으로", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}
