package com.example.bannerexampleapp.ui.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bannerexampleapp.ui.features.main.LocalNavigationProvider
import com.example.bannerexampleapp.ui.features.main.MainScreen
import com.example.bannerexampleapp.ui.features.start.StartScreen

enum class AppNavigationRoute(val routeName: String) {
    START("/start"),
    ROOT("/root")
}

fun NavHostController.navigate(route: AppNavigationRoute, option: (NavOptionsBuilder.(NavHostController) -> Unit)? = null) {
    navigate(route.routeName) {
        option?.let {
            it(this@navigate)
        }
    }
}

@Composable
fun AppNavigationRoute.screen(controller: NavHostController,
                              backStackEntry: NavBackStackEntry
) {
    when(this) {
        AppNavigationRoute.START -> {
            StartScreen()
        }
        AppNavigationRoute.ROOT -> {
            MainScreen()
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    CompositionLocalProvider(LocalNavigationProvider provides navController) {
        NavHost(navController = navController,
            startDestination = AppNavigationRoute.START.routeName) {
            AppNavigationRoute.values().forEach {
                screen(navController, it)
            }
        }
    }
}

fun NavGraphBuilder.screen(controller: NavHostController, route: AppNavigationRoute) {
    composable(
        route = route.routeName
    ) {
        route.screen(controller, it)
    }
}