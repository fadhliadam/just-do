package com.adam.justdo.ui.component.task

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.adam.justdo.domain.model.Task
import com.adam.justdo.util.parseDate
import java.sql.Date
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskDialog(
    listName: String,
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onSave: (Task) -> Unit,
) {
    val scrollState = rememberScrollState()
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val parseSelectedDate =
        if (selectedDate != null) Date.valueOf(selectedDate.toString()) else null
    var openCalendarDialog by remember { mutableStateOf(false) }
    var importantTodoCheck by remember { mutableStateOf(false) }
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    val titleTextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
    )
    val textFieldColor = TextFieldDefaults.colors(
        unfocusedContainerColor = Color.LightGray.copy(0.4f),
        focusedContainerColor = Color.LightGray.copy(0.4f),
        errorContainerColor = Color.LightGray.copy(0.4f),
        unfocusedPlaceholderColor = Color.Gray,
        focusedPlaceholderColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    )
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(shape = MaterialTheme.shapes.medium) {
            Column(Modifier.padding(14.dp)) {
                TextField(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    placeholder = { Text("New Task", style = titleTextStyle) },
                    singleLine = true,
                    textStyle = titleTextStyle,
                    colors = textFieldColor,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        if (taskTitle.isNotBlank()) {
                            onDismissRequest()
                        }
                    }),
                )
                TextField(
                    modifier = Modifier
                        .height(132.dp)
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = taskDescription,
                    onValueChange = { taskDescription = it },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    placeholder = { Text("Description") },
                    colors = textFieldColor
                )
                Row(
                    modifier = Modifier.horizontalScroll(scrollState).padding(horizontal = 16.dp),
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
                            onClick = {
                                onSave(
                                    Task(
                                        title = taskTitle,
                                        description = taskDescription,
                                        dueDate = parseSelectedDate,
                                        listGroup = listName,
                                        isCompleted = false,
                                        isImportant = importantTodoCheck,
                                        dateAdded = Date.valueOf(LocalDate.now().toString()),
                                    )
                                )
                            }
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