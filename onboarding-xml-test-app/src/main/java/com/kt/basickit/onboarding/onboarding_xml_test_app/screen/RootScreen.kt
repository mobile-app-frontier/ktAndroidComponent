package com.kt.basickit.onboarding.onboarding_xml_test_app.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kt.basickit.onboarding.onboarding_xml_test_app.navigator.AppNavigationRoute
import com.kt.basickit.onboarding.onboarding_xml_test_app.navigator.onboarding.LocalNavigationProvider
@Composable
fun RootScreen() {
    val navController = LocalNavigationProvider.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Start Onboarding", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp,))
        Button(onClick = {
            navController.navigate(AppNavigationRoute.ONBOARIDNG.routeName)
        }) {
            Text("onboarding start", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}
