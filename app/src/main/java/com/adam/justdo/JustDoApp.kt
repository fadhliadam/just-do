package com.adam.justdo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adam.justdo.ui.navigation.Screen
import com.adam.justdo.ui.screen.home.HomeScreen

@Composable
fun JustDoApp() {
    val navHostController = rememberNavController()
    
    NavHost(navController = navHostController, startDestination = Screen.Home.route ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
    }
}