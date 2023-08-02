package com.adam.justdo.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.StickyNote2
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adam.justdo.util.parseDate
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    dueDate: String?,
    importantChecked: Boolean,
    checkBoxChecked: Boolean,
    onImportantChecked: (Boolean) -> Unit,
    onCheckedBoxChange: (Boolean) -> Unit,
    todoItemOnClick: () -> Unit,
) {
    val formattedDate = if (dueDate != null) parseDate(LocalDate.parse(dueDate.toString())) else ""
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
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (!formattedDate.isNullOrBlank()) {
                            Icon(
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(end = 4.dp),
                                imageVector = Icons.Outlined.CalendarToday,
                                contentDescription = "Due date icon"
                            )
                            Text(
                                text = formattedDate,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        if (description.isNotBlank() && !formattedDate.isNullOrBlank()) {
                            Text(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                text = "·",
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        if (description.isNotBlank()) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Outlined.StickyNote2,
                                contentDescription = "Description icon"
                            )
                        }
                    }
                }
            }
            ImportantToggleButton(
                checked = importantChecked,
                onCheckedChange = { onImportantChecked(it) }
            )
        }
    }
}