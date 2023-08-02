package com.adam.justdo.ui.component.task

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import com.adam.justdo.util.parseDate
import java.time.LocalDate
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskDialog(
    group: Group,
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onSave: (Task) -> Unit,
) {
    val scrollState = rememberScrollState()
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val parseSelectedDate = if (selectedDate != null) selectedDate.toString() else null
    var openCalendarDialog by remember { mutableStateOf(false) }
    var importantTodoCheck by remember { mutableStateOf(false) }
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val task = Task(
        title = taskTitle,
        description = taskDescription,
        dueDate = parseSelectedDate,
        groupId = group.id,
        isImportant = importantTodoCheck,
        isCompleted = false,
        dateAdded = LocalDateTime.now().toString(),
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(shape = MaterialTheme.shapes.medium) {
            Column(Modifier.padding(14.dp)) {
                TaskGroupTextFields(
                    task = task,
                    focusRequester = focusRequester,
                    taskTitle = taskTitle,
                    onTitleChange = { taskTitle = it },
                    taskDescription = taskDescription,
                    onNoteChange = { taskDescription = it },
                    onDone = { onSave(task) }
                )
                Row(
                    modifier = Modifier
                        .horizontalScroll(scrollState)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SetDueDateButton(
                        modifier = Modifier.padding(end = 8.dp),
                        selected = selectedDate != null,
                        dateFormatted = parseDate(selectedDate),
                        onClickTrailingIcon = { selectedDate = null },
                        onClick = { openCalendarDialog = true },
                    )
                    FilterChip(
                        selected = importantTodoCheck,
                        label = {
                            Text(text = "Important")
                        },
                        leadingIcon = {
                            val icon =
                                if (!importantTodoCheck) Icons.Outlined.Flag else Icons.Filled.Flag
                            Icon(
                                imageVector = icon,
                                contentDescription = "Important Toggle Task"
                            )
                        },
                        onClick = { importantTodoCheck = !importantTodoCheck },
                    )
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 4.dp)
                ) {
                    Row {
                        TextButton(
                            modifier = Modifier.padding(end = 4.dp),
                            onClick = { onCancel() }
                        ) {
                            Text(
                                text = "Cancel",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        TextButton(
                            enabled = taskTitle.isNotBlank(),
                            modifier = Modifier.padding(end = 14.dp),
                            onClick = { onSave(task) }
                        ) {
                            Text(
                                text = "Save",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }

    if (openCalendarDialog) {
        SetDueDateDialog(
            date = selectedDate,
            onDone = { selectedDate = it },
            onDismissRequest = { openCalendarDialog = false }
        )
    }
}