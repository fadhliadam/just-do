package com.adam.justdo.ui.component.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adam.justdo.ui.navigation.ListType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskModalBottomSheet(
    listType: ListType,
    onDismissRequest: () -> Unit,
) {
    var text by remember { mutableStateOf("") }
    ModalBottomSheet(onDismissRequest = { onDismissRequest() }) {
        TextField(value = text, onValueChange = {text = it})
    }
}