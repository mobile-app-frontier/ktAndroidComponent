package com.kt.basickit.onboarding.onboarding_xml_test_app.navigator.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.kt.basickit.onboarding.OnBoardingViewModel
import com.kt.basickit.onboarding.XmlOnBoardingRoute
import com.kt.basickit.onboarding.onboarding_xml_test_app.databinding.OnboardingPageOneBinding
import com.kt.basickit.onboarding.onboarding_xml_test_app.databinding.OnboardingPageTwoBinding

class OnBoardingPageOne(
    override val routeName: String = "/onBoardingPageOne",
    override val binding: (inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> OnboardingPageOneBinding,
    val updateBiding: (OnboardingPageOneBinding, OnBoardingViewModel) -> Unit
) : XmlOnBoardingRoute  {
    override fun <T : ViewBinding> update(
        viewBinding: T,
        onBoardingViewModel: OnBoardingViewModel
    ) {
        if (viewBinding is OnboardingPageOneBinding) {
            updateBiding(viewBinding, onBoardingViewModel)
        } else {
            throw TypeCastException("ViewBinding is not OnboardingPageOneBinding format")
        }
    }
}

class OnBoardingPageTwo(
    override val routeName: String = "/onBoardingPageTwo",
    override val binding: (inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> OnboardingPageTwoBinding,
    val updateBiding: (OnboardingPageTwoBinding, OnBoardingViewModel) -> Unit
) : XmlOnBoardingRoute  {
    override fun <T : ViewBinding> update(
        viewBinding: T,
        onBoardingViewModel: OnBoardingViewModel
    ) {
        if (viewBinding is OnboardingPageTwoBinding) {
            updateBiding(viewBinding, onBoardingViewModel)
        } else {
            throw TypeCastException("ViewBinding is not OnboardingPageOneBinding format")
        }
    }
}
