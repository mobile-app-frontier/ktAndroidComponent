package com.kt.basickit.onboarding.onboarding_xml_test_app.screen.step

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.Navigation
import com.kt.basickit.onboarding.OnBoardingScreen
import com.kt.basickit.onboarding.onboarding_xml_test_app.OnBoardingPageOne
import com.kt.basickit.onboarding.onboarding_xml_test_app.OnBoardingPageTwo
import com.kt.basickit.onboarding.onboarding_xml_test_app.R
import com.kt.basickit.onboarding.onboarding_xml_test_app.databinding.OnboardingPageOneBinding
import com.kt.basickit.onboarding.onboarding_xml_test_app.databinding.OnboardingPageTwoBinding

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

        onBackPressed {
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("Dialog Title")
                .setMessage("Dialog Message")
                .setPositiveButton("OK") { dialog, which ->
                    Log.d("onBoarding", "exit")
                    Navigation.findNavController(requireActivity(), R.id.nav_host).popBackStack()
                }
                .setNegativeButton("Cancel", null)
                .create()
            dialog.show()
        }

        val composeView = view.findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            OnBoardingScreen(
                steps = listOf(
                    OnBoardingPageOne(
                        binding = OnboardingPageOneBinding::inflate,
                        updateBiding = { viewBinding, onBoardingViewModel ->
                            viewBinding.onboardingViewModel = onBoardingViewModel
                        }
                    ),
                    OnBoardingPageTwo(
                        binding = OnboardingPageTwoBinding::inflate,
                        updateBiding = { viewBinding, onBoardingViewModel ->
                            viewBinding.onboardingViewModel = onBoardingViewModel
                            viewBinding.endOnboarding.setOnClickListener {
                                Navigation.findNavController(requireActivity(), R.id.nav_host).navigate(
                                    R.id.action_firstFragment_to_homeFragment
                                )
                            }
                        }
                    ),
                ),
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
