package com.adam.justdo.ui.navigation

sealed class Screen(var route: String) {
    object Home : Screen("home_screen")
    object Important : Screen("Important")
    object Today : Screen("Today")
    object All : Screen("All")
}