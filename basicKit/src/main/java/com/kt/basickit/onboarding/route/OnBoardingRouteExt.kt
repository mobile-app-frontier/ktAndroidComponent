package com.kt.basickit.onboarding.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.OnBoardingViewModel

@Composable
fun OnBoardingRoute.draw(
    onBoardingNavController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel,
) {
    when (val routeType = this) {
        is XmlOnBoardingRoute -> {
            AndroidViewBinding(
                factory = routeType.binding,
                update = {
                    routeType.update(this, onBoardingViewModel)
                }
            )
        }

        is ComposeOnBoardingRoute -> {
            routeType.screen(onBoardingNavController, onBoardingViewModel)
        }
    }
}
