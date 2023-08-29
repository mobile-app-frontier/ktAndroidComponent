package com.kt.basickit.onboarding.onboarding_example_app.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.onboarding.navigator.AppNavigationRoute
import com.example.onboarding.navigator.onboarding.LocalNavigationProvider

@Composable
fun RootScreen() {
    val navController = LocalNavigationProvider.current

    Column {
        Text("Start Onboarding?")
        Spacer(Modifier.height(10.dp,))
        Button(onClick = {
            navController.navigate(AppNavigationRoute.ONBOARIDNG.routeName)
        }) {
            Text("onboarding start")
        }
    }
}
