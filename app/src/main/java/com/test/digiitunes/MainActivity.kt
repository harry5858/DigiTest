package com.test.digiitunes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.test.digiitunes.ui.components.TopNavigationBar
import com.test.digiitunes.ui.navigation.BaseNavHost
import com.test.digiitunes.ui.navigation.Screen
import com.test.digiitunes.ui.theme.DigiItunesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DigiItunesTheme {
                val currentRoute = navController.currentBackStackEntryAsState()
                val title = remember(currentRoute.value) {
                    when(currentRoute.value?.destination?.route) {
                        Screen.HomeScreen.route -> "Home"
                        Screen.SavedResultScreen.route -> "Saved Result"
                        else -> "Video Preview"
                    }
                }
                val canBack = remember(currentRoute.value) {
                    when(currentRoute.value?.destination?.route) {
                        Screen.HomeScreen.route -> false
                        else -> true
                    }
                }
                Scaffold(
                    topBar = {
                        TopNavigationBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            title = title,
                            canBack = canBack,
                            onBackClick = { navController.popBackStack() },
                            onGoToSavedResultClick = { navController.navigate(Screen.SavedResultScreen.route) }
                        )
                    }
                ) {  paddingValues ->
                    BaseNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        navController = navController
                    )
                }
            }
        }
    }
}