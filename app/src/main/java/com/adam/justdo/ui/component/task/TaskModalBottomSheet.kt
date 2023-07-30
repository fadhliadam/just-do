package com.adam.justdo.ui.component.task

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
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
import com.adam.justdo.util.parseDate
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskModalBottomSheet(
    onDismissRequest: () -> Unit,
    onSave: () -> Unit,
) {
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
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
        modifier = Modifier.imePadding(),
        containerColor = containerColor,
        dragHandle = {},
        onDismissRequest = {
            onDismissRequest()
        },
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
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            value = taskDescription,
            onValueChange = { taskDescription = it },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
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
                    val icon = if (!importantTodoCheck) Icons.Outlined.Flag else Icons.Filled.Flag
                    Icon(
                        imageVector = icon,
                        contentDescription = "Important Toggle Task"
                    )
                },
                onClick = { importantTodoCheck = !importantTodoCheck },
            )
        }
        TextButton(
            modifier = Modifier
                .padding(end = 14.dp, bottom = 4.dp)
                .align(Alignment.End),
            onClick = { onSave() }
        ) {
            Text(
                text = "Save",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
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