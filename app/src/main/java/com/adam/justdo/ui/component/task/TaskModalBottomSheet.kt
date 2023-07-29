package com.adam.justdo.ui.component.task

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskModalBottomSheet(
    onDismissRequest: () -> Unit,
) {
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val dateFormat =
        if (selectedDate?.year == LocalDate.now().year) "E, MMM dd" else "E, MMM dd, yyyy"
    val formattedDate = selectedDate?.format(DateTimeFormatter.ofPattern(dateFormat))
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
        unfocusedContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        unfocusedPlaceholderColor = Color.Gray,
        focusedPlaceholderColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    )
    val containerColor =
        if (!isSystemInDarkTheme()) Color.White else MaterialTheme.colorScheme.background
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    ModalBottomSheet(
        containerColor = containerColor,
        dragHandle = {},
        onDismissRequest = {
            onDismissRequest()
        },
        shape = MaterialTheme.shapes.medium,
    ) {
        TextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            value = taskTitle,
            onValueChange = { taskTitle = it },
            placeholder = { Text("New Task", style = titleTextStyle) },
            maxLines = 8,
            textStyle = titleTextStyle,
            colors = textFieldColor,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                if (taskTitle.isNotBlank()) {
                    onDismissRequest()
                }
            }),
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            value = taskDescription,
            onValueChange = { taskDescription = it },
            placeholder = { Text("Description") },
            colors = textFieldColor
        )
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SetDueDateButton(
                modifier = Modifier.padding(end = 8.dp),
                selected = selectedDate != null,
                dateFormatted = formattedDate,
                onClickTrailingIcon = { selectedDate = null },
                onClick = { openCalendarDialog = true },
            )
            FilterChip(
                selected = importantTodoCheck,
                label = {
                    Text(text = "Important")
                },
                leadingIcon = {
                    val icon = if (!importantTodoCheck) Icons.Outlined.Flag else Icons.Filled.Flag
                    Icon(
                        imageVector = icon,
                        contentDescription = "Important Toggle Task"
                    )
                },
                onClick = { importantTodoCheck = !importantTodoCheck },
            )
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