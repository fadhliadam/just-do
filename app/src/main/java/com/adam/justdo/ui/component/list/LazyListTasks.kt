package com.adam.justdo.ui.component.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adam.justdo.R
import com.adam.justdo.domain.model.Task
import com.adam.justdo.ui.component.TodoItemCard
import com.adam.justdo.ui.component.task.TaskDialog

@Composable
fun LazyListTasks(
    listTask: List<Task>,
    listTaskCompleted: List<Task>,
    onSaveEditTask: (Task) -> Unit,
    onDeleteTask: (Task) -> Unit,
) {
    val listState = rememberLazyListState()
    val interactionSource = MutableInteractionSource()
    var expandItem by remember { mutableStateOf(false) }
    val listTaskNotCompleted = listTask.filter { !it.isCompleted }

    LazyColumn(
        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
        state = listState
    ) {
        if (listTaskCompleted.isNotEmpty()) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .clickable(interactionSource = interactionSource, indication = null) {
                            expandItem = !expandItem
                        }
                ) {
                    Text(
                        "Completed Tasks",
                        modifier = Modifier.weight(1F),
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = if (expandItem) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = "image",
                        tint = Color.Black,
                    )
                }
            }
            if (expandItem) {
                items(listTaskCompleted) { item ->
                    var isImportant by remember { mutableStateOf(item.isImportant) }
                    var isCompleted by remember { mutableStateOf(item.isCompleted) }
                    var openDialog by remember { mutableStateOf(false) }
                    var openDeleteDialog by remember { mutableStateOf(false) }
                    TodoItemCard(
                        modifier = Modifier.padding(vertical = 4.dp),
                        title = item.title,
                        description = item.description,
                        dueDate = item.dueDate,
                        importantChecked = isImportant,
                        checkBoxChecked = isCompleted,
                        onImportantChecked = { isImportant = !isImportant },
                        onCheckedBoxChange = { isCompleted = !isCompleted },
                        todoItemOnClick = {
                            openDialog = true
                        },
                    )
                    if (openDialog) TaskDialog(
                        task = item,
                        onDismissRequest = { openDialog = false },
                        onDelete = { openDeleteDialog = !openDeleteDialog },
                        onCancel = { openDialog = false },
                        onSave = {
                            onSaveEditTask(item)
                            openDialog = false
                        },
                    )
                    if (openDeleteDialog) {
                        DeleteDialog(
                            title = stringResource(R.string.delete_task),
                            description = stringResource(R.string.delete_task_desc),
                            onDelete = { onDeleteTask(item) },
                            onDismissRequest = { openDeleteDialog = false }
                        )
                    }
                }
            }
        }
        items(listTaskNotCompleted) { item ->
            var isImportant by remember { mutableStateOf(item.isImportant) }
            var isCompleted by remember { mutableStateOf(item.isCompleted) }
            var openDialog by remember { mutableStateOf(false) }
            var openDeleteDialog by remember { mutableStateOf(false) }
            TodoItemCard(
                modifier = Modifier.padding(vertical = 4.dp),
                title = item.title,
                description = item.description,
                dueDate = item.dueDate,
                importantChecked = isImportant,
                checkBoxChecked = isCompleted,
                onImportantChecked = { isImportant = !isImportant },
                onCheckedBoxChange = { isCompleted = !isCompleted },
                todoItemOnClick = {
                    openDialog = true
                },
            )
            if (openDialog) TaskDialog(
                task = item,
                onDismissRequest = { openDialog = false },
                onDelete = { openDeleteDialog = !openDeleteDialog },
                onCancel = { openDialog = false },
                onSave = {
                    onSaveEditTask(item)
                    openDialog = false
                },
            )
            if (openDeleteDialog) {
                DeleteDialog(
                    title = stringResource(R.string.delete_task),
                    description = stringResource(R.string.delete_task_desc),
                    onDelete = { onDeleteTask(item) },
                    onDismissRequest = { openDeleteDialog = false }
                )
            }
        }
    }
}