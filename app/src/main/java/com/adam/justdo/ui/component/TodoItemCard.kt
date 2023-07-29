package com.adam.justdo.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemCard(
    modifier: Modifier = Modifier,
    title: String,
    importantChecked: Boolean,
    checkBoxChecked: Boolean,
    onImportantChecked: (Boolean) -> Unit,
    onCheckedBoxChange: (Boolean) -> Unit,
    todoItemOnClick: () -> Unit,
) {
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
                        onCheckedBoxChange(it)
                    },
                    interactionSource = MutableInteractionSource()
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            ImportantToggleButton(
                checked = importantChecked,
                onCheckedChange = { onImportantChecked(it) }
            )
        }
    }
}