package com.adam.justdo.ui.navigation

sealed class Screen(var route: String) {
    object Home : Screen("home_screen")
}