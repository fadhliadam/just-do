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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import com.adam.justdo.ui.component.list.LazyListTasks
import com.adam.justdo.ui.component.list.ListScreenTopBar
import com.adam.justdo.ui.component.list.MoreActionModalBottomSheet
import com.adam.justdo.ui.component.task.CreateTaskDialog
import com.adam.justdo.ui.navigation.ListType

@Composable
fun ListScreen(
    navHostController: NavHostController,
    group: Group,
    listType: ListType,
    listVM: ListVM = hiltViewModel(),
) {
    var listTaskNotCompleted by remember { mutableStateOf(emptyList<Task>()) }
    var listTaskCompleted by remember { mutableStateOf(emptyList<Task>()) }
    var openMoreBottomSheet by remember { mutableStateOf(false) }
    var openAddTaskDialog by remember { mutableStateOf(false) }
    val taskFlow = listVM.taskFlow.collectAsState()

    LaunchedEffect(listVM.taskFlow) {
        if (listType != ListType.Important && group.id != null) {
            listVM.getTaskByGroupId(group.id)
        } else {
            listVM.getAllTasks()
        }
    }

    taskFlow.value?.let {
        listTaskCompleted = it.filter { task -> task.isCompleted }
        listTaskNotCompleted = it.filter { task -> !task.isCompleted }
    }

    Scaffold(
        topBar = {
            ListScreenTopBar(
                navHostController = navHostController,
                groupName = group.groupName,
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
                listTaskNotCompleted = listTaskNotCompleted,
                listTaskCompleted = listTaskCompleted,
                onImportantCheck = { id, isImportant -> listVM.updateImportant(id, isImportant) },
                onCompletedCheck = { id, isCompleted -> listVM.updateCompleted(id, isCompleted) },
                onSaveEditTask = { task -> listVM.upsertTask(task) },
                onDeleteTask = { task -> listVM.deleteTask(task) }
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
            listName = group.groupName,
            listType = listType,
            isListCompletedEmpty = listTaskCompleted.isEmpty(),
            onRename = {
                listVM.renameGroup(Group(group.id, it))
                openMoreBottomSheet = false
            },
            onDeleteList = {
                listVM.deleteGroup(group)
                openMoreBottomSheet = false
                navHostController.popBackStack()
            },
            onDeleteCompletedTask = { /*TODO*/ },
            onDismissRequest = { openMoreBottomSheet = false }
        )
    } else if (openAddTaskDialog) {
        if (group.id == null) {
            CreateTaskDialog(
                group = Group(null, ""),
                onDismissRequest = { openAddTaskDialog = false },
                onCancel = { openAddTaskDialog = false },
                onSave = {
                    listVM.upsertTask(it)
                    openAddTaskDialog = false
                }
            )
        } else {
            CreateTaskDialog(
                group = group,
                onDismissRequest = { openAddTaskDialog = false },
                onCancel = { openAddTaskDialog = false },
                onSave = {
                    listVM.upsertTask(it)
                    openAddTaskDialog = false
                }
            )
        }
    }
}