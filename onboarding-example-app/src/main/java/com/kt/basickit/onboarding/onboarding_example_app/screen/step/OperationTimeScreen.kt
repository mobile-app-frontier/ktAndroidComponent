package com.kt.basickit.onboarding.onboarding_example_app.screen.step

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.OnBoardingViewModel
import com.kt.basickit.onboarding.onboarding_example_app.navigator.onboarding.AppOnBoardingRoute

class TimeViewModel : ViewModel() {
    var tempValue: Int = 0
}

@Composable
fun OperationTimeScreen(
    onBoardingNavController: NavHostController,
    onBoardingController: OnBoardingViewModel,
) {
    val navBackStackEntry =
        remember { onBoardingNavController.getBackStackEntry(AppOnBoardingRoute.OperationTime.routeName) }
    val timeViewModel = viewModel<TimeViewModel>(navBackStackEntry)
    val isChecked = remember {
        mutableStateOf(false)
    }
    BackHandler {
        onBoardingController.prev()
    }
    Column {

        Text("time screen")
        Button(onClick = {
            isChecked.value = !isChecked.value
            timeViewModel.tempValue += 1
        }) {
            Text("이벤트가 있습니다 ${isChecked.value} ${timeViewModel.tempValue}")
        }
        Button(onClick = {
            if (isChecked.value) {
                onBoardingController.next()
            } else {

            }
        }) {
            Text("다음으로")
        }
    }
}
