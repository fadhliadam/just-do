package com.adam.justdo.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.navigation.NavHostController
import com.adam.justdo.data.local.TaskDummy
import com.adam.justdo.ui.component.TodoGroupButton
import com.adam.justdo.ui.component.home.HomeTopBar
import com.adam.justdo.ui.navigation.ListType
import com.adam.justdo.ui.navigation.Screen
import com.adam.justdo.util.filterAndSortTask

@Composable
fun HomeScreen(navHostController: NavHostController) {
    var openCreateListTask by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            HomeTopBar(
                onProfilePictureClick = { /*TODO*/ }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column {
                TodoGroupButton(
                    icon = Icons.Filled.ListAlt,
                    iconTint = Color.Yellow.copy(green = 0.5f),
                    title = "All Tasks",
                    todoCount = TaskDummy.taskDummy.count(),
                    onClick = {
                        navHostController.navigate(Screen.All.route)
                    }
                )
                TodoGroupButton(
                    icon = Icons.Filled.Flag,
                    iconTint = MaterialTheme.colorScheme.tertiary,
                    title = "Important",
                    todoCount = filterAndSortTask(
                        ListType.Important,
                        "Important",
                        TaskDummy.taskDummy
                    ).count(),
                    onClick = {
                        navHostController.navigate(Screen.Important.route)
                    }
                )
                TodoGroupButton(
                    icon = Icons.Filled.Today,
                    iconTint = MaterialTheme.colorScheme.secondary,
                    title = "Today",
                    todoCount = filterAndSortTask(
                        ListType.Important,
                        "Today",
                        TaskDummy.taskDummy
                    ).count(),
                    onClick = {
                        navHostController.navigate(Screen.Today.route)
                    }
                )
                Divider(thickness = 1.dp)
            }
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp),
                onClick = { openCreateListTask = !openCreateListTask }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "New List")
                Text(text = "New List")
            }
        }
    }
    if (openCreateListTask) {
        CreateListTask(
            onDismissRequest = { openCreateListTask = false },
            onCancel = { openCreateListTask = false },
            onCreate = { openCreateListTask = false },
        )
    }
}

@Composable
fun CreateListTask(
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onCreate: (String) -> Unit,
) {
    val focusRequest = remember { FocusRequester() }
    var listName by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
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
                    text = "Create new list",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                TextField(
                    modifier = Modifier.height(100.dp).focusRequester(focusRequest),
                    value = listName,
                    onValueChange = {
                        if (it.length <= 50) listName = it
                        isError = listName.count() >= 50
                    },
                    suffix = {
                        Text(
                            text = "${listName.count()}/50",
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
                        onDone = { onCreate(listName) }
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
                            onClick = { onCreate(listName) },
                            enabled = listName.isNotBlank()
                        ) {
                            Text(text = "Create")
                        }
                    }
                }
            }
        }
    }
}