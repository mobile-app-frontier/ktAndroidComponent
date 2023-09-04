package com.kt.basickit.onboarding.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.viewbinding.ViewBinding
import com.kt.basickit.onboarding.OnBoardingViewModel

interface OnBoardingVerticalSideArea

interface XmlOnBoardingVerticalSideArea : OnBoardingVerticalSideArea {
    val binding: (inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> ViewBinding
    fun <T : ViewBinding> update(viewBinding: T, onBoardingViewModel: OnBoardingViewModel)
}

interface ComposeOnBoardingVerticalSideArea : OnBoardingVerticalSideArea {
    @Composable
    fun Compose(navHostController: NavHostController, onBoardingViewModel: OnBoardingViewModel)
}
