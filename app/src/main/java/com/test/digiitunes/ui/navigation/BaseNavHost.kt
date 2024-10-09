package com.test.digiitunes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.digiitunes.ui.exoPlayer.ExoPlayerScreen
import com.test.digiitunes.ui.home.HomeScreen
import com.test.digiitunes.ui.saved.SavedResultScreen

private enum class Route {
    HOME, SAVED, EXOPLAYER
}

sealed class Screen(val route: String) {
    data object HomeScreen: Screen(route = Route.HOME.name)
    data object SavedResultScreen: Screen(route = Route.SAVED.name)
    data object ExoPlayerScreen: Screen(route = Route.EXOPLAYER.name)
}

@Composable
fun BaseNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.SavedResultScreen.route) {
            SavedResultScreen(navController)
        }
        composable(route = "${Screen.ExoPlayerScreen.route}/{url}") { navBackStackEntry ->
            val url = navBackStackEntry.arguments?.getString("url")
            url?.let { safeUrl ->
                ExoPlayerScreen(safeUrl)
            }
        }
    }
}