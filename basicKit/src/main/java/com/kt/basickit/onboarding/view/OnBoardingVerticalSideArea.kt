package com.kt.basickit.onboarding.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.viewbinding.ViewBinding
import com.kt.basickit.onboarding.OnBoardingViewModel

/**
 * OnBoarding 상/하단에 표출할 화면 정의.
 */
interface OnBoardingVerticalSideArea

/**
 * 표출할 화면을 xml로 정의한 경우 사용.
 */
interface XmlOnBoardingVerticalSideArea : OnBoardingVerticalSideArea {
    val binding: (inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> ViewBinding
    fun <T : ViewBinding> update(viewBinding: T, onBoardingViewModel: OnBoardingViewModel)
}

/**
 * 표출할 화면을 compose로 정의한 경우 사용.
 */
interface ComposeOnBoardingVerticalSideArea : OnBoardingVerticalSideArea {
    @Composable
    fun Compose(navHostController: NavHostController, onBoardingViewModel: OnBoardingViewModel)
}
