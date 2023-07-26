package com.adam.justdo.ui.component.group

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuButtonItem(
    val id: String,
    val icon: ImageVector,
    val iconColor: Color,
    val title: String,
)