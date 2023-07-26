package com.adam.justdo.ui.navigation

sealed class Screen(var route: String) {
    object Home : Screen("home")
    object Important : Screen("important")
}