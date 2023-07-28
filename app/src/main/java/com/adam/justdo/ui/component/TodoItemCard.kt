package com.adam.justdo.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemCard(
    modifier: Modifier = Modifier,
    title: String,
    onImportantChecked: (Boolean) -> Unit,
    onCheckedBoxChange: (Boolean) -> Unit,
    todoItemOnClick: () -> Unit,
) {
    var importantIcon by remember { mutableStateOf(Icons.Outlined.Flag) }
    var importantChecked by remember { mutableStateOf(false) }
    var checkBoxChecked by remember { mutableStateOf(false) }
    Card(
        modifier = modifier,
        onClick = {
            todoItemOnClick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkBoxChecked,
                    onCheckedChange = {
                        checkBoxChecked = it
                        onCheckedBoxChange(it)
                    },
                    interactionSource = MutableInteractionSource()
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            IconToggleButton(
                checked = importantChecked,
                onCheckedChange = {
                    importantChecked = it
                    importantIcon = if (it) {
                        Icons.Filled.Flag
                    } else {
                        Icons.Outlined.Flag
                    }
                    onImportantChecked(it)
                }
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = importantIcon,
                    tint = Color.Red.copy(alpha = 0.8f, green = 0.5f),
                    contentDescription = "today icon"
                )
            }
        }
    }
}