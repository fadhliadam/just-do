package com.adam.justdo.ui.component.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskModalBottomSheet(
    onDismissRequest: () -> Unit,
) {
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val dateFormat = selectedDate.toString()
    var openCalendarDialog by remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()
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

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    ModalBottomSheet(
        containerColor = containerColor,
        dragHandle = {},
        onDismissRequest = { onDismissRequest() }
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .focusRequester(focusRequester),
            value = taskTitle,
            onValueChange = { taskTitle = it },
            placeholder = { Text("New Task", style = titleTextStyle) },
            maxLines = 8,
            textStyle = titleTextStyle,
            colors = textFieldColor,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(onSend = {
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
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            ElevatedAssistChip(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Today,
                        contentDescription = "Due date icon"
                    )
                },
                trailingIcon = {
                    if (selectedDate != null) {
                        Icon(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .clickable { selectedDate = null },
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "cancel"
                        )
                    }
                },
                label = {
                    if (selectedDate == null) {
                        Text(text = "Set due date")
                    } else {
                        Text(text = dateFormat)
                    }
                },
                onClick = { openCalendarDialog = true }
            )
        }
    }

    if (openCalendarDialog) {
        SetDueDateDialog(
            selectedDate = selectedDate,
            onDone = { selectedDate = it },
            onDismissRequest = { openCalendarDialog = false }
        )
    }
}