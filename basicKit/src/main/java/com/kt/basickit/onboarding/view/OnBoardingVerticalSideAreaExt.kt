package com.kt.basickit.onboarding.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.OnBoardingViewModel

/**
 * 적절한 상/하단 화면을 [OnBoardingVerticalSideArea] 타입에 따라 그린다.
 */
@Composable
fun OnBoardingVerticalSideArea.draw(
    navHostController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel
) {
    when (val sideAreaType = this) {
        is XmlOnBoardingVerticalSideArea -> {
            AndroidViewBinding(factory = sideAreaType.binding) {
                sideAreaType.update(this, onBoardingViewModel)
            }
        }

        is ComposeOnBoardingVerticalSideArea -> {
            sideAreaType.Compose(navHostController, onBoardingViewModel)
        }
    }
}
