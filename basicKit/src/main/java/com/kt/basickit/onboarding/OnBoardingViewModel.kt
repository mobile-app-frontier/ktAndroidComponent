package com.kt.basickit.onboarding

import kotlinx.coroutines.flow.StateFlow

/**
 * OnBoarding 스탭 간 이동 지원.
 */
sealed interface OnBoardingViewModel {
    fun next()

    fun prev()

    val controllerState: StateFlow<OnBoardingState>
}
