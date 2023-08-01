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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.adam.justdo.data.local.TaskDummy
import com.adam.justdo.ui.component.TodoItemCard
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
    var isMoreButtonPressed by remember { mutableStateOf(false) }
    var isAddTaskButtonPressed by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            ListScreenTopBar(
                navHostController = navHostController,
                listName = listName,
                onClickMore = { isMoreButtonPressed = !isMoreButtonPressed },
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
                        onDelete = { /*TODO*/ },
                        onCancel = { openDialog = false },
                        onSave = { openDialog = false },
                    )
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp),
                onClick = { isAddTaskButtonPressed = !isAddTaskButtonPressed }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add List Button"
                )
            }
        }
    }
    if (isMoreButtonPressed) {
        MoreActionModalBottomSheet(
            listName = listName,
            listType = listType,
            isListEmpty = false,
            onDismissRequest = { isMoreButtonPressed = false }
        )
    } else if (isAddTaskButtonPressed) {
        CreateTaskDialog(
            listName = listName,
            onDismissRequest = { isAddTaskButtonPressed = false },
            onCancel = { isAddTaskButtonPressed = false },
            onSave = { isAddTaskButtonPressed = false }
        )
    }
}