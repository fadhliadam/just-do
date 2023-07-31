package com.adam.justdo.ui.component.task

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.adam.justdo.domain.model.Task
import com.adam.justdo.ui.theme.Red40
import com.adam.justdo.util.parseDate
import java.sql.Date
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDialog(
    task: Task,
    onDismissRequest: () -> Unit,
    onDelete: () -> Unit,
    onCancel: () -> Unit,
    onSave: (Task) -> Unit,
) {
    val scrollState = rememberScrollState()
    var selectedDate by remember {
        mutableStateOf(
            if (task.dueDate != null) LocalDate.parse(
                task.dueDate.toString()
            ) else null
        )
    }
    val parseSelectedDate =
        if (selectedDate != null) Date.valueOf(selectedDate.toString()) else null
    var openCalendarDialog by remember { mutableStateOf(false) }
    var importantTodoCheck by remember { mutableStateOf(task.isImportant) }
    var taskTitle by remember { mutableStateOf(task.title) }
    var taskDescription by remember { mutableStateOf(task.description) }
    val focusRequester = remember { FocusRequester() }

    val editedTask = Task(
        title = taskTitle,
        description = taskDescription,
        dueDate = parseSelectedDate,
        listGroup = task.listGroup,
        isCompleted = false,
        isImportant = importantTodoCheck,
        dateAdded = task.dateAdded,
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(shape = MaterialTheme.shapes.medium) {
            Column(Modifier.padding(14.dp)) {
                TaskGroupTextFields(
                    task = editedTask,
                    focusRequester = focusRequester,
                    taskTitle = taskTitle,
                    onTitleChange = { taskTitle = it },
                    taskDescription = taskDescription,
                    onNoteChange = { taskDescription = it },
                    onDone = {
                        onSave(editedTask)
                        onDismissRequest()
                    }
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
                Row(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { onDelete() },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            tint = Red40,
                            contentDescription = "Delete Icon Button",
                        )
                    }
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
                            onClick = { onSave(editedTask) }
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