package com.adam.justdo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adam.justdo.data.local.ListNameDummy
import com.adam.justdo.ui.navigation.ListType
import com.adam.justdo.ui.navigation.Screen
import com.adam.justdo.ui.screen.home.HomeScreen
import com.adam.justdo.ui.screen.list.ListScreen

@Composable
fun JustDoApp() {
    val navController = rememberNavController()
    val listName = ListNameDummy.listName

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Important.route) {
            ListScreen(
                navHostController = navController,
                listName = Screen.Important.route,
                listType = ListType.Important
            )
        }
        composable(route = Screen.Today.route) {
            ListScreen(
                navHostController = navController,
                listName = Screen.Today.route,
                listType = ListType.Important
            )
        }
        composable(route = Screen.All.route) {
            ListScreen(
                navHostController = navController,
                listName = Screen.All.route,
                listType = ListType.Important
            )
        }
        listName.forEach {item ->
            composable(route = item.listName) {
                ListScreen(
                    navHostController = navController,
                    listName = item.listName,
                    listType = ListType.Optional,
                )
            }
        }
    }
}