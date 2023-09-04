package com.kt.basickit.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.route.OnBoardingRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * OnBoarding 진행 관리.
 *
 * @constructor OnBoarding 내 화면 전환에 사용할 [NavHostController], 진행할 스탭 리스트, 시작 스탭
 */
internal class OnBoardingViewModelImpl(
    val navController: NavHostController,
    steps: List<OnBoardingRoute>,
    startStep: Int,
) : ViewModel(), OnBoardingViewModel {

    private val _state: MutableStateFlow<OnBoardingState> = MutableStateFlow(
        OnBoardingInProgress(
            steps = steps,
            currentStep = startStep,
        )
    )

    /**
     * OnBoarding 진행 상태를 바라볼 수 있음.
     */
    override val controllerState: StateFlow<OnBoardingState>
        get() = _state.asStateFlow()

    /**
     * OnBoarding 을 다음 스탭으로 이동 시킴.
     * 호출 시 더 이상 진행할 스탭이 없다면 [OnBoardingComplete] 상태를 발행.
     */
    override fun next() {
        Log.d("next", "pressed ${controllerState.value.currentStep}")
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

    /**
     * OnBoarding 을 이전 스탭으로 이동 시킴.
     * 호출 시 현재 스탭이 첫번째 스탭이었다면 [OnBoardingCancel] 상태를 발행.
     */
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
