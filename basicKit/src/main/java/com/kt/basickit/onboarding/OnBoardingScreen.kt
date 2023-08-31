package com.kt.basickit.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * OnBoarding 스탭을 출력 하는 컴포저블.
 *
 * @param steps 사용자가 정의한 OnBoarding 스탭 리스트
 * @param startStep 시작 스탭 인덱스. 기본 값으로 0 사용.
 * @param onBoardingTopArea O스nBoarding 각 스탭 화면에 관계 없이 유지할 상단 영역 컴포저블. 없으면 출력 하지 않는다.
 * @param onBoardingBottomArea OnBoarding 각 스탭 화면에 관계 없이 유지할 하단 영역 컴포저블. 없으면 출력 하지 않는다.
 */
@Composable
fun <CustomStep : OnBoardingRoute> OnBoardingScreen(
    steps: List<CustomStep>,
    startStep: Int = 0,
    onBoardingTopArea: (@Composable (NavHostController, OnBoardingViewModel) -> Unit)? = null,
    onBoardingBottomArea: (@Composable (NavHostController, OnBoardingViewModel) -> Unit)? = null,
) {
    val onBoardingNavController = rememberNavController()
    val onBoardingViewModel: OnBoardingViewModelImpl = remember {
        OnBoardingViewModelImpl(
            navController = onBoardingNavController,
            steps = steps,
            startStep = startStep,
        )
    }
    Column {
        onBoardingTopArea?.let {
            onBoardingTopArea(onBoardingNavController, onBoardingViewModel)
        }
        OnBoardingNavHost(
            onBoardingNavController = onBoardingNavController,
            onBoardingViewModel = onBoardingViewModel,
            steps = steps,
        )
        onBoardingBottomArea?.let {
            onBoardingBottomArea(onBoardingNavController, onBoardingViewModel)
        }
    }
}

/**
 * 스탭 이동 시 내비게이션 처리.
 * @param onBoardingNavController OnBoarding 내비게이션에 사용되는 [NavHostController]
 * @param onBoardingViewModel OnBoarding 진행에 사용.
 * @param steps 사용자가 입력한 OnBoarding 스탭들.
 */
@Composable
private fun <CustomStep : OnBoardingRoute> OnBoardingNavHost(
    onBoardingNavController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel,
    steps: List<CustomStep>,
) {
    NavHost(
        navController = onBoardingNavController,
        startDestination = steps.first().routeName,
    ) {
        if (steps.first() is ComposeOnBoardingRoute) {
            steps.forEach { onBoardingRoute ->
                val onBoardingRoute = onBoardingRoute as ComposeOnBoardingRoute
                composable(
                    route = onBoardingRoute.routeName
                ) {
                    onBoardingRoute.screen(onBoardingNavController, onBoardingViewModel)
                }
            }
        } else {
            steps.forEach { onBoardingRoute ->
                composable(
                    route = onBoardingRoute.routeName
                ) {
                    val onBoardingRoute = onBoardingRoute as XmlOnBoardingRoute
                    AndroidViewBinding(
                        factory = onBoardingRoute.binding,
                        update = {
                            onBoardingRoute.update(this, onBoardingViewModel)
                        }
                    )
                }
            }
        }
    }
}
