package com.adam.justdo.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.adam.justdo.data.local.TaskDummy
import com.adam.justdo.ui.component.TodoGroupButton
import com.adam.justdo.ui.component.home.HomeBottomBar
import com.adam.justdo.ui.component.home.HomeTopBar
import com.adam.justdo.ui.navigation.ListType
import com.adam.justdo.ui.navigation.Screen
import com.adam.justdo.util.filterAndSortTask

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Scaffold(
        bottomBar = {
            HomeBottomBar(
                onClickIcon1 = { /*TODO*/ },
                onClickIcon2 = { /*TODO*/ },
                onClickIconFAB = { /*TODO*/ }
            )
        },
        topBar = {
            HomeTopBar(
                onProfilePictureClick = { /*TODO*/ }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            Column {
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
            }
        }
    }
}