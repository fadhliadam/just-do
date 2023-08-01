package com.adam.justdo.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun GroupTaskDialog(
    value: String,
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onSave: (String) -> Unit,
) {
    val focusRequest = remember { FocusRequester() }
    var groupName by remember { mutableStateOf(value) }
    var isError by remember { mutableStateOf(false) }
    val title = if (value.isBlank()) "Create new list" else "Rename list"
    val textFieldColor = TextFieldDefaults.colors(
        unfocusedContainerColor = Color.LightGray.copy(0.4f),
        focusedContainerColor = Color.LightGray.copy(0.4f),
        errorContainerColor = Color.LightGray.copy(0.4f),
        unfocusedPlaceholderColor = Color.Gray,
        focusedPlaceholderColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    )

    LaunchedEffect(Unit) {
        focusRequest.requestFocus()
    }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(shape = MaterialTheme.shapes.medium) {
            Column(
                modifier = Modifier.padding(14.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 14.dp, top = 8.dp),
                    text = title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                TextField(
                    modifier = Modifier
                        .height(100.dp)
                        .focusRequester(focusRequest),
                    value = groupName,
                    onValueChange = {
                        if (it.length <= 50) groupName = it
                        isError = groupName.count() >= 50
                    },
                    suffix = {
                        Text(
                            text = "${groupName.count()}/50",
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (isError) MaterialTheme.colorScheme.error else
                                MaterialTheme.colorScheme.onSurface
                        )
                    },
                    supportingText = {
                        if (isError) Text(
                            text = "List title has exceeded the word limit",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                    },
                    keyboardActions = KeyboardActions(
                        onDone = { onSave(groupName) }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    isError = isError,
                    maxLines = 2,
                    colors = textFieldColor
                )
                Box(modifier = Modifier.align(Alignment.End)) {
                    Row {
                        TextButton(onClick = { onCancel() }) {
                            Text(text = "Cancel")
                        }
                        TextButton(
                            onClick = { onSave(groupName) },
                            enabled = groupName.isNotBlank()
                        ) {
                            Text(text = "Save")
                        }
                    }
                }
            }
        }
    }
}