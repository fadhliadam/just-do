package com.adam.justdo.ui.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.adam.justdo.ui.component.TodoItemCard
import com.adam.justdo.ui.component.list.AddTaskModalBottomSheet
import com.adam.justdo.ui.component.list.ListScreenTopBar
import com.adam.justdo.ui.component.list.MoreActionModalBottomSheet
import com.adam.justdo.ui.navigation.ListType

@Composable
fun ListScreen(
    navHostController: NavHostController,
    listName: String,
    listType: ListType,
) {
    val listState = rememberLazyListState()
    val listTodo = listOf('a', 'b', 'c')
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
                .fillMaxHeight()
        ) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                state = listState
            ) {
                itemsIndexed(items = listTodo) { index, item ->
                    TodoItemCard(
                        modifier = Modifier.padding(vertical = 4.dp),
                        title = "title",
                        onImportantChecked = { /*TODO*/ },
                        onCheckedBoxChange = { /*TODO*/ },
                        todoItemOnClick = { /*TODO*/ }
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
            listType = listType,
            isListEmpty = false,
            onDismissRequest = { isMoreButtonPressed = false }
        )
    } else if (isAddTaskButtonPressed) {
        AddTaskModalBottomSheet(
            listType = listType,
            onDismissRequest = { isAddTaskButtonPressed = false }
        )
    }
}