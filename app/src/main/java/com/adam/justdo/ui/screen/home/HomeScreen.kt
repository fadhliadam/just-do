package com.adam.justdo.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.adam.justdo.data.local.TaskDummy
import com.adam.justdo.ui.component.TodoGroupButton
import com.adam.justdo.ui.component.home.CreateListTask
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