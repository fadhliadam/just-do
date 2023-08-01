package com.adam.justdo.ui.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.adam.justdo.R
import com.adam.justdo.data.local.TaskDummy
import com.adam.justdo.ui.component.TodoItemCard
import com.adam.justdo.ui.component.list.DeleteDialog
import com.adam.justdo.ui.component.list.ListScreenTopBar
import com.adam.justdo.ui.component.list.MoreActionModalBottomSheet
import com.adam.justdo.ui.component.task.CreateTaskDialog
import com.adam.justdo.ui.component.task.TaskDialog
import com.adam.justdo.ui.navigation.ListType
import com.adam.justdo.util.filterAndSortTask

@Composable
fun ListScreen(
    navHostController: NavHostController,
    listName: String,
    listType: ListType,
) {
    val listState = rememberLazyListState()
    val listTaskDummy = remember { filterAndSortTask(listType, listName, TaskDummy.taskDummy) }
    val listTaskCompleted = listTaskDummy.filter { it.isCompleted }
    var openMoreBottomSheet by remember { mutableStateOf(false) }
    var openAddTaskDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            ListScreenTopBar(
                navHostController = navHostController,
                listName = listName,
                onClickMore = { openMoreBottomSheet = !openMoreBottomSheet },
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                state = listState
            ) {
                items(listTaskDummy) { item ->
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
                        onSave = { openDialog = false },
                    )
                    if (openDeleteDialog) {
                        DeleteDialog(
                            title = stringResource(R.string.delete_task),
                            description = stringResource(R.string.delete_task_desc),
                            onDelete = { /*TODO*/ },
                            onDismissRequest = { openDeleteDialog = false }
                        )
                    }
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp),
                onClick = { openAddTaskDialog = !openAddTaskDialog }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add List Button"
                )
            }
        }
    }
    if (openMoreBottomSheet) {
        MoreActionModalBottomSheet(
            listName = listName,
            listType = listType,
            isListCompletedEmpty = listTaskCompleted.isEmpty(),
            onRename = { /*TODO*/ },
            onDeleteList = { /*TODO*/ },
            onDeleteCompletedTask = { /*TODO*/ },
            onDismissRequest = { openMoreBottomSheet = false }
        )
    } else if (openAddTaskDialog) {
        CreateTaskDialog(
            listName = listName,
            onDismissRequest = { openAddTaskDialog = false },
            onCancel = { openAddTaskDialog = false },
            onSave = { openAddTaskDialog = false }
        )
    }
}