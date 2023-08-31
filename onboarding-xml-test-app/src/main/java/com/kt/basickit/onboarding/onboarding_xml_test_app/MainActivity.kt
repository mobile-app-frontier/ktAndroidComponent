package com.kt.basickit.onboarding.onboarding_xml_test_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import com.kt.basickit.onboarding.onboarding_xml_test_app.navigator.AppNavHost

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val composeView = findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            AppNavHost()
        }

    }
}
