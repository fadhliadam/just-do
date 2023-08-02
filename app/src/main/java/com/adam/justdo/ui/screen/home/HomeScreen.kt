package com.adam.justdo.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.adam.justdo.data.local.TaskDummy
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.ui.component.GroupTaskDialog
import com.adam.justdo.ui.component.TodoGroupButton
import com.adam.justdo.ui.component.home.HomeTopBar
import com.adam.justdo.ui.navigation.ListType
import com.adam.justdo.ui.navigation.Screen
import com.adam.justdo.util.filterAndSortTask

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeVM: HomeVM = hiltViewModel()
) {
    var openCreateGroupTask by remember { mutableStateOf(false) }
    var listGroup by remember { mutableStateOf(emptyList<Group>()) }
    val groupFlow = homeVM.groupFlow.collectAsState()
    var newGroupName by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        homeVM.getAllGroup()
    }

    groupFlow.value?.let {
        listGroup = it
    }

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
                    todoCount = TaskDummy.taskDummy.count { task -> !task.isCompleted },
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
                    ).count { task -> !task.isCompleted },
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
                    ).count { task -> !task.isCompleted },
                    onClick = {
                        navHostController.navigate(Screen.Today.route)
                    }
                )
                Divider(thickness = 1.dp)
                LazyColumn(state = listState) {
                    items(listGroup) { item ->
                        TodoGroupButton(
                            icon = Icons.Filled.List,
                            iconTint = MaterialTheme.colorScheme.secondary,
                            title = item.groupName,
                            todoCount = filterAndSortTask(
                                ListType.Optional,
                                item.groupName,
                                TaskDummy.taskDummy
                            ).count { task -> !task.isCompleted },
                            onClick = {
                                navHostController.navigate(item.groupName)
                            }
                        )
                    }
                }
            }
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp),
                onClick = { openCreateGroupTask = !openCreateGroupTask }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "New List")
                Text(text = "New List")
            }
        }
    }
    if (openCreateGroupTask) {
        GroupTaskDialog(
            value = newGroupName,
            onDismissRequest = { openCreateGroupTask = false },
            onCancel = { openCreateGroupTask = false },
            onSave = {
                newGroupName = it
                homeVM.upsertGroup(
                    Group(groupName = newGroupName)
                )
                openCreateGroupTask = false
            },
        )
    }
}