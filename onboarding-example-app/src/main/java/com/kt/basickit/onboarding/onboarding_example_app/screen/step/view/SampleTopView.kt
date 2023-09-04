package com.kt.basickit.onboarding.onboarding_example_app.screen.step.view

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kt.basickit.onboarding.OnBoardingViewModel

@Composable
fun SampleTopView(
    onBoardingNavController: NavHostController,
    onBoardingController: OnBoardingViewModel,
) {
    val state = onBoardingController.controllerState.collectAsStateWithLifecycle()
    OnBoardingProgressBar(targetValue = state.value.currentStep.toFloat() / (state.value.steps.size.toFloat()-1))
}

@Composable
fun OnBoardingProgressBar(
    targetValue: Float,
    durationMillis: Int = 1500,
    easing: Easing = FastOutSlowInEasing
) {
    val progressAnimation by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = tween(durationMillis = durationMillis, easing = easing)
    )
    LinearProgressIndicator(
        progress = progressAnimation, modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .clip(RoundedCornerShape(20.dp))
    )
}
