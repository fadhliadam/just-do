package com.adam.justdo.ui.navigation

sealed class ListType(val type: String) {
    object Important: ListType("important")
    object Optional: ListType("optional")
}
