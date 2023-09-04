package com.kt.basickit.onboarding.onboarding_xml_test_app.screen.step

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.kt.basickit.onboarding.OnBoardingScreen
import com.kt.basickit.onboarding.OnBoardingViewModel
import com.kt.basickit.onboarding.route.XmlOnBoardingRoute
import com.kt.basickit.onboarding.onboarding_xml_test_app.OnBoardingPageOne
import com.kt.basickit.onboarding.onboarding_xml_test_app.OnBoardingPageTwo
import com.kt.basickit.onboarding.onboarding_xml_test_app.R
import com.kt.basickit.onboarding.onboarding_xml_test_app.databinding.OnboardingPageOneBinding
import com.kt.basickit.onboarding.onboarding_xml_test_app.databinding.OnboardingPageTwoBinding
import com.kt.basickit.onboarding.onboarding_xml_test_app.databinding.OnboardingTopFragmentBinding
import com.kt.basickit.onboarding.view.XmlOnBoardingVerticalSideArea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view.findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            OnBoardingScreen<XmlOnBoardingRoute, XmlOnBoardingVerticalSideArea>(
                steps = listOf(
                    OnBoardingPageOne(
                        binding = OnboardingPageOneBinding::inflate,
                        updateBiding = { viewBinding, onBoardingViewModel ->
                            viewBinding.onboardingViewModel = onBoardingViewModel
                            requireActivity().onBackPressedDispatcher.addCallback(
                                viewLifecycleOwner,
                                object : OnBackPressedCallback(true) {
                                    override fun handleOnBackPressed() {
                                        val dialog = AlertDialog.Builder(requireContext())
                                            .setTitle("온보딩 종료")
                                            .setMessage("확인을 누르면 온보딩을 종료합니다.")
                                            .setPositiveButton("OK") { dialog, which ->
                                                Navigation.findNavController(requireActivity(), R.id.nav_host).popBackStack()
                                            }
                                            .setNegativeButton("Cancel", null)
                                            .create()
                                        dialog.show()
                                    }
                                }
                            )
                        }
                    ),
                    OnBoardingPageTwo(
                        binding = OnboardingPageTwoBinding::inflate,
                        updateBiding = { viewBinding, onBoardingViewModel ->
                            viewBinding.onboardingViewModel = onBoardingViewModel
                            viewBinding.endOnboardingButton.setOnClickListener {
                                Navigation.findNavController(requireActivity(), R.id.nav_host).navigate(
                                    R.id.action_firstFragment_to_homeFragment
                                )
                            }
                            requireActivity().onBackPressedDispatcher.addCallback(
                                viewLifecycleOwner,
                                object : OnBackPressedCallback(true) {
                                    override fun handleOnBackPressed() {
                                        onBoardingViewModel.prev()
                                    }
                                }
                            )
                        }
                    ),
                ),
                onBoardingTopArea = object: XmlOnBoardingVerticalSideArea {
                    override val binding: (inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> ViewBinding = OnboardingTopFragmentBinding::inflate
                    override fun <T : ViewBinding> update(
                        viewBinding: T,
                        onBoardingViewModel: OnBoardingViewModel
                    ) {
                        val viewBinding = viewBinding as OnboardingTopFragmentBinding
                        viewBinding.onboardingViewModel = onBoardingViewModel
                        CoroutineScope(Dispatchers.Main).launch {
                            onBoardingViewModel.controllerState.collect {state ->
                                viewBinding.onboardingStepText.text = state.currentStep.toString()
                            }
                        }
                    }
                }
            )
        }
    }
}

fun Fragment.onBackPressed(action: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                action.invoke()
            }
        }
    )
}
