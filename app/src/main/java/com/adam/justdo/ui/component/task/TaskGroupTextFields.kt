package com.adam.justdo.ui.component.task

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.adam.justdo.domain.model.Task

@Composable
fun TaskGroupTextFields(
    task: Task,
    focusRequester: FocusRequester,
    taskTitle: String,
    onTitleChange: (String) -> Unit,
    taskDescription: String,
    onNoteChange: (String) -> Unit,
    onDone: (Task) -> Unit,
) {
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
    TextField(
        modifier = Modifier
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .padding(8.dp),
        value = taskTitle,
        onValueChange = { onTitleChange(it) },
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
                onDone(task)
            }
        }),
    )
    TextField(
        modifier = Modifier
            .height(132.dp)
            .fillMaxWidth()
            .padding(8.dp),
        value = taskDescription,
        onValueChange = { onNoteChange(it) },
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
        placeholder = { Text("add description") },
        colors = textFieldColor
    )
}