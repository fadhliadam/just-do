package com.adam.justdo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adam.justdo.ui.navigation.Screen
import com.adam.justdo.ui.screen.home.HomeScreen
import com.adam.justdo.ui.screen.list.ListScreen

@Composable
fun JustDoApp() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Screen.Home.route ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Important.route) {
            ListScreen(
                navHostController = navController,
                isOptional = false
            )
        }
    }
}