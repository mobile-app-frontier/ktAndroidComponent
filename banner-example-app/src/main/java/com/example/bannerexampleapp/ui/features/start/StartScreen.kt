package com.example.bannerexampleapp.ui.features.start

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bannerexampleapp.core.base.StateViewModelListener
import com.example.bannerexampleapp.core.logger.Logger
import com.example.bannerexampleapp.ui.features.main.LocalNavigationProvider
import com.example.bannerexampleapp.ui.navigator.AppNavigationRoute
import com.example.bannerexampleapp.ui.navigator.navigate
import com.kt.basickit.banner.BannerManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@NonRestartableComposable
@Composable
fun StartScreen(
    screenViewModel: StartViewModel = hiltViewModel(),
) {
    val activity = (LocalContext.current as? Activity)
    val navController = LocalNavigationProvider.current

    LaunchedEffect(key1 = "", block = {
        screenViewModel.fetchInitialData()
    })

    StateViewModelListener(stateViewModel = screenViewModel, listen = { state ->
        when(state) {
            is StartState.FailToInitialize -> {
                // 앱 실행 실패 팝업
                Logger.e("Fail to start App!!")
                activity?.finish()
            }

            is StartState.Success -> {
                navController.navigate(route = AppNavigationRoute.ROOT) {
                    // Banner Manager initialize
                    BannerManager.initialize(
                        localBannerPolicySetter = { localBannerPolicy ->
                            screenViewModel.saveLocalBannerPolicy(localBannerPolicy)
                        },
                        localBannerPolicyGetter = {
                            return@initialize screenViewModel.readLocalBannerPolicy()
                        },
                        bannerPolicy = state.bannerPolicy
                    )

                    navController.popBackStack()
                    it.graph.setStartDestination(AppNavigationRoute.ROOT.routeName)
                }
            }
            else -> {
                // do nothing
            }
        }
    })

    StartScreenContent()
}



@Composable
fun StartScreenContent() {
    Box (
        modifier= Modifier.fillMaxSize()
    ){
        // Splash
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}