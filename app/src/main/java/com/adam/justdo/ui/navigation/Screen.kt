package com.adam.justdo.ui.navigation

sealed class Screen(var route: String) {
    object Home : Screen("home")
    object Important : Screen("important")
    object Today : Screen("Today")
    object All : Screen("All")
}