package com.adam.justdo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.ui.navigation.ListType
import com.adam.justdo.ui.navigation.Screen
import com.adam.justdo.ui.screen.NavigationVM
import com.adam.justdo.ui.screen.home.HomeScreen
import com.adam.justdo.ui.screen.list.ListScreen

@Composable
fun JustDoApp() {
    val navController = rememberNavController()
    val navigationVM: NavigationVM = hiltViewModel()
    var listGroup by remember { mutableStateOf(emptyList<Group>()) }
    val groupFlow = navigationVM.groupFlow.collectAsState()

    LaunchedEffect(Unit) {
        navigationVM.getAllGroup()
    }

    groupFlow.value?.let {
        listGroup = it
    }

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Important.route) {
            ListScreen(
                navHostController = navController,
                groupName = Screen.Important.route,
                listType = ListType.Important
            )
        }
        composable(route = Screen.Today.route) {
            ListScreen(
                navHostController = navController,
                groupName = Screen.Today.route,
                listType = ListType.Important
            )
        }
        composable(route = Screen.All.route) {
            ListScreen(
                navHostController = navController,
                groupName = Screen.All.route,
                listType = ListType.Important
            )
        }
        listGroup.forEach { item ->
            composable(route = item.groupName) {
                ListScreen(
                    navHostController = navController,
                    groupName = item.groupName,
                    listType = ListType.Optional,
                )
            }
        }
    }
}