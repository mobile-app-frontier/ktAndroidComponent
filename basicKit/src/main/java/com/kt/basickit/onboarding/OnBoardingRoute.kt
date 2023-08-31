package com.kt.basickit.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.viewbinding.ViewBinding

/**
 * OnBoarding에서 진행할 개별 스탭의 구성 요소.
 *
 * @param routeName 스탭의 고유한 경로명.
 * @param screen 해당 스탭의 화면. [NavHostController], [OnBoardingViewModel]은 내부에서 구현되어 있고, 스탭 화면 이동 및 이전 스탭의 ViewModel을 가져오는 등의 작업 가능.
 */
interface OnBoardingRoute {
    val routeName: String
}

interface XmlOnBoardingRoute : OnBoardingRoute {
    override val routeName: String
    val binding: (inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> ViewBinding
    fun<T: ViewBinding> update(viewBinding:T, onBoardingViewModel:OnBoardingViewModel)
}

interface ComposeOnBoardingRoute: OnBoardingRoute {
    override val routeName: String
    val screen: @Composable (NavHostController, OnBoardingViewModel) -> Unit
}
