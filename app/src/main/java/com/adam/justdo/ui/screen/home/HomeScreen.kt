package com.adam.justdo.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.adam.justdo.data.local.TaskDummy
import com.adam.justdo.ui.component.TodoGroupButton
import com.adam.justdo.ui.component.TodoItemCard
import com.adam.justdo.ui.component.home.HomeBottomBar
import com.adam.justdo.ui.component.home.HomeTopBar
import com.adam.justdo.ui.navigation.Screen

@Composable
fun HomeScreen(navHostController: NavHostController) {
    val listState = rememberLazyListState()
    val listTaskDummy = remember { TaskDummy.taskDummy }
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
                onProfilePictureClick = { /*TODO*/ },
                onClickSearch = { /*TODO*/ }
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
                    modifier = Modifier.padding(bottom = 8.dp),
                    icon = Icons.Filled.Flag,
                    iconTint = Color.Red.copy(alpha = 0.8f, green = 0.5f),
                    title = "Important",
                    todoCount = 1,
                    onClick = {
                        navHostController.navigate(Screen.Important.route)
                    }
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 14.dp)
                        .fillMaxHeight()
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "Today",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.DarkGray.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Medium
                    )
                    LazyColumn(state = listState) {
                        itemsIndexed(items = listTaskDummy) { index, item ->
                            var isImportant by remember { mutableStateOf(item.isImportant) }
                            var isCompleted by remember { mutableStateOf(item.isCompleted) }
                            TodoItemCard(
                                modifier = Modifier.padding(vertical = 4.dp),
                                title = item.title,
                                description = item.description,
                                dueDate = item.dueDate,
                                importantChecked = isImportant,
                                checkBoxChecked = isCompleted,
                                onImportantChecked = { isImportant = !isImportant },
                                onCheckedBoxChange = { isCompleted = !isCompleted },
                                todoItemOnClick = {},
                            )
                        }
                    }
                }
            }
        }
    }
}