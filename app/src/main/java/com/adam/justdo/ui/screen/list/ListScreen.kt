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
import com.adam.justdo.ui.component.list.ListScreenModalBottomSheet
import com.adam.justdo.ui.component.list.ListScreenTopBar

@Composable
fun ListScreen(
    navHostController: NavHostController,
    isOptional: Boolean,
) {
    val listState = rememberLazyListState()
    val listTodo = listOf('a', 'b', 'c')
    var isPressed by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            ListScreenTopBar(
                navHostController = navHostController,
                onClickMore = { isPressed = !isPressed },
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
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add List Button"
                )
            }
        }
    }
    if (isPressed) {
        ListScreenModalBottomSheet(
            isOptional = isOptional,
            onDismissRequest = { isPressed = false }
        )
    }
}