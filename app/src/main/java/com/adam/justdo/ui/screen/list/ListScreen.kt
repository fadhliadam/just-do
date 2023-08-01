package com.adam.justdo.ui.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.adam.justdo.ui.component.list.LazyListTasks
import com.adam.justdo.ui.component.list.ListScreenTopBar
import com.adam.justdo.ui.component.list.MoreActionModalBottomSheet
import com.adam.justdo.ui.component.task.CreateTaskDialog
import com.adam.justdo.ui.navigation.ListType
import com.adam.justdo.util.filterAndSortTask

@Composable
fun ListScreen(
    navHostController: NavHostController,
    groupName: String,
    listType: ListType,
) {
    val listTaskDummy = remember { filterAndSortTask(listType, groupName, TaskDummy.taskDummy) }
    val listTaskCompleted = listTaskDummy.filter { it.isCompleted }
    var openMoreBottomSheet by remember { mutableStateOf(false) }
    var openAddTaskDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            ListScreenTopBar(
                navHostController = navHostController,
                groupName = groupName,
                onClickMore = { openMoreBottomSheet = !openMoreBottomSheet },
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyListTasks(
                listTask = listTaskDummy,
                listTaskCompleted = listTaskCompleted,
                onSaveEditTask = { /*TODO*/ },
                onDeleteTask = { /*TODO*/ }
            )
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
            listName = groupName,
            listType = listType,
            isListCompletedEmpty = listTaskCompleted.isEmpty(),
            onRename = { /*TODO*/ },
            onDeleteList = { /*TODO*/ },
            onDeleteCompletedTask = { /*TODO*/ },
            onDismissRequest = { openMoreBottomSheet = false }
        )
    } else if (openAddTaskDialog) {
        CreateTaskDialog(
            listName = groupName,
            onDismissRequest = { openAddTaskDialog = false },
            onCancel = { openAddTaskDialog = false },
            onSave = { openAddTaskDialog = false }
        )
    }
}