package com.example.bannerexampleapp.ui.features.main

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kt.basickit.banner.BannerManager
import com.kt.basickit.banner.view.popupBanner.PopupBannerView
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->

        LaunchedEffect(key1 = Unit, block = {
            BannerManager.startPopupBanner(context)

            this.launch {
                BannerManager.landingType.collect {
                    // TODO: Landing
                    Toast.makeText(
                        context,
                        "Landing to $it",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

//        PopupBannerView()

        Column(
            modifier = Modifier.padding(
                paddingValues = paddingValues
            )
                .padding(10.dp)
        ) {
            Text("Default Banner View")

            BannerManager.DefaultBannerView(
                category = "callHistory",
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}