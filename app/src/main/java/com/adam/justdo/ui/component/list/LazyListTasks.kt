package com.adam.justdo.ui.component.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adam.justdo.R
import com.adam.justdo.data.local.entity.Task
import com.adam.justdo.ui.component.TodoItemCard
import com.adam.justdo.ui.component.task.TaskDialog

@Composable
fun LazyListTasks(
    listTaskNotCompleted: List<Task>,
    listTaskCompleted: List<Task>,
    onImportantCheck: (Int, Boolean) -> Unit,
    onCompletedCheck: (Int, Boolean) -> Unit,
    onSaveEditTask: (Task) -> Unit,
    onDeleteTask: (Task) -> Unit,
) {
    var expandItem by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        items(listTaskNotCompleted) { item ->
            var openDialog by remember { mutableStateOf(false) }
            var openDeleteDialog by remember { mutableStateOf(false) }
            TodoItemCard(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp),
                title = item.title,
                description = item.description,
                dueDate = item.dueDate,
                importantChecked = item.isImportant,
                checkBoxChecked = item.isCompleted,
                onImportantChecked = {
                    item.id?.let { id -> onImportantCheck(id, it) }
                },
                onCheckedBoxChange = {
                    item.id?.let { id -> onCompletedCheck(id, it) }
                },
            ) {
                openDialog = true
            }
            if (openDialog) TaskDialog(
                task = item,
                onDismissRequest = { openDialog = false },
                onDelete = { openDeleteDialog = !openDeleteDialog },
                onCancel = { openDialog = false },
                onSave = {
                    onSaveEditTask(it)
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
        if (listTaskCompleted.isNotEmpty()) {
            item {
                Box(
                    modifier = Modifier.clickable {
                        expandItem = !expandItem
                    }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Completed Tasks",
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = if (expandItem) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                            contentDescription = "Completed tasks section extend",
                            tint = Color.Black,
                        )
                    }
                }
            }
            if (expandItem) {
                items(listTaskCompleted) { item ->
                    var openDialog by remember { mutableStateOf(false) }
                    var openDeleteDialog by remember { mutableStateOf(false) }
                    TodoItemCard(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp),
                        title = item.title,
                        description = item.description,
                        dueDate = item.dueDate,
                        importantChecked = item.isImportant,
                        checkBoxChecked = item.isCompleted,
                        onImportantChecked = {
                            item.id?.let { id -> onImportantCheck(id, it) }
                        },
                        onCheckedBoxChange = {
                            item.id?.let { id -> onCompletedCheck(id, it) }
                        },
                    ) {
                        openDialog = true
                    }
                    if (openDialog) TaskDialog(
                        task = item,
                        onDismissRequest = { openDialog = false },
                        onDelete = { openDeleteDialog = !openDeleteDialog },
                        onCancel = { openDialog = false },
                        onSave = {
                            onSaveEditTask(it)
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
    }
}