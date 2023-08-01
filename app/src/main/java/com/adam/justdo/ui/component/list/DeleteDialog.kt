package com.adam.justdo.ui.component.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DeleteDialog(
    title: String,
    description: String,
    onDelete: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(shape = MaterialTheme.shapes.medium) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = description,
                )
                Row(Modifier.align(Alignment.End)) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = { onDelete() }) {
                        Text(text = "Delete", color = Color.Red)
                    }
                }
            }
        }
    }
}