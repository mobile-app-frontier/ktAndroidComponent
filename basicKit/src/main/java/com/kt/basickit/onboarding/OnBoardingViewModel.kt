package com.kt.basickit.onboarding

import kotlinx.coroutines.flow.StateFlow

// onboarding viewmodel
sealed interface OnBoardingViewModel {
    fun next()

    fun prev()

    val controllerState: StateFlow<OnBoardingState>
}
