package com.kt.basickit.onboarding.onboarding_xml_test_app.navigator.onboarding

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalNavigationProvider =
    staticCompositionLocalOf<NavHostController> { error("No navigation host controller provided.") }
