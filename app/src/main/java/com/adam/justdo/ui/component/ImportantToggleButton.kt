package com.adam.justdo.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ImportantToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    var importantIcon by remember { mutableStateOf(Icons.Outlined.Flag) }
    IconToggleButton(
        checked = checked,
        onCheckedChange = {
            onCheckedChange(it)
        }
    ) {
        importantIcon = if (checked) {
            Icons.Filled.Flag
        } else {
            Icons.Outlined.Flag
        }
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = importantIcon,
            tint = MaterialTheme.colorScheme.tertiary,
            contentDescription = "Important Icon Toggle"
        )
    }
}