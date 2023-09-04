package com.kt.basickit.onboarding.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.OnBoardingViewModel

/**
 * 적절한 스탭 화면을 [OnBoardingRoute] 타입에 따라 그린다.
 */
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
