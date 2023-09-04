package com.kt.basickit.onboarding

/**
 * OnBoarding 진행 상태 관리.
 * 진행할 OnBoarding 스탭 리스트 및 현재 진행 중인 스탭을 알고 있으며,
 * 이를 통해 OnBoarding의 완료, 진행, 취소 상태를 파악 한다.
 */
sealed interface OnBoardingState {
    val steps: List<OnBoardingRoute>
    val currentStep: Int

    val isCancel: Boolean get() = this.currentStep == 0
    val nextRoute: OnBoardingRoute get() = steps[currentStep+1]
    val totalStepSize: Int get() = steps.size
}

/**
 * OnBoarding 진행을 취소 시킬 때 발행 되는 상태.
 */
data class OnBoardingCancel(
    override val steps: List<OnBoardingRoute>,
    override val currentStep: Int,
) : OnBoardingState {
}

/**
 * OnBoarding 진행 중인 상태.
 */
data class OnBoardingInProgress(
    override val steps: List<OnBoardingRoute>,
    override val currentStep: Int,
) : OnBoardingState {
}

/**
 * OnBoarding 완료 상태.
 */
data class OnBoardingComplete(
    override val steps: List<OnBoardingRoute>,
    override val currentStep: Int,
) : OnBoardingState {
}
