package com.kt.basickit.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class OnBoardingViewModelImpl(
    val navController: NavHostController,
    steps: List<OnBoardingRoute>,
    currentStep: Int,
) : ViewModel(), OnBoardingViewModel {

    private val _state: MutableStateFlow<OnBoardingState> = MutableStateFlow(
        OnBoardingInProgress(
            steps = steps,
            currentStep = currentStep,
        )
    )

    override val controllerState: StateFlow<OnBoardingState>
        get() = _state.asStateFlow()

    override fun next() {
        if (controllerState.value.totalStepSize - 1 == controllerState.value.currentStep) {
            viewModelScope.launch {
                _state.emit(
                    OnBoardingComplete(
                        steps = controllerState.value.steps,
                        currentStep = controllerState.value.currentStep,
                    )
                )
            }
        } else {
            val route = controllerState.value.nextRoute
            viewModelScope.launch {
                _state.emit(
                    OnBoardingInProgress(
                        steps = controllerState.value.steps,
                        currentStep = controllerState.value.currentStep + 1,
                    )
                )
            }
            navController.navigate(route.routeName)
        }
    }

    override fun prev() {
        /// 첫번째 스탭에서 이전으로 돌아가려 한다면 CancelState를 발행한다.
        if (controllerState.value.isCancel) {
            viewModelScope.launch {
                _state.emit(
                    OnBoardingCancel(
                        steps = controllerState.value.steps,
                        currentStep = controllerState.value.currentStep,
                    )
                )
            }
        } else {
            // 뒤로 가기 시 이전 스탭으로 이동한다.
            viewModelScope.launch {
                _state.emit(
                    OnBoardingInProgress(
                        steps = controllerState.value.steps,
                        currentStep = controllerState.value.currentStep - 1,
                    )
                )
            }
            navController.popBackStack()
        }
    }
}
