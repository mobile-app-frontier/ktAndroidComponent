package com.kt.basickit.onboarding

sealed interface OnBoardingState {
    val steps: List<OnBoardingRoute>
    val currentStep: Int

    val isCancel: Boolean get() = this.currentStep == 0
    val nextRoute: OnBoardingRoute get() = steps[currentStep+1]
    val totalStepSize: Int get() = steps.size
}

data class OnBoardingCancel(
    override val steps: List<OnBoardingRoute>,
    override val currentStep: Int,
) : OnBoardingState {
}

data class OnBoardingInProgress(
    override val steps: List<OnBoardingRoute>,
    override val currentStep: Int,
) : OnBoardingState {
}

data class OnBoardingComplete(
    override val steps: List<OnBoardingRoute>,
    override val currentStep: Int,
) : OnBoardingState {
}
