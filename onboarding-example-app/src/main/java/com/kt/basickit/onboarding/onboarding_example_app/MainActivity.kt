package com.kt.basickit.onboarding.onboarding_example_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kt.basickit.onboarding.onboarding_example_app.navigator.AppNavHost
import com.kt.basickit.onboarding.onboarding_example_app.ui.theme.ComposableKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableKitTheme {
                AppNavHost()
            }
        }
    }
}
