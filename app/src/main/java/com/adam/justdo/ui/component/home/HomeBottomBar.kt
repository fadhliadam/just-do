package com.adam.justdo.ui.component.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun HomeBottomBar(
    onClickIcon1: () -> Unit,
    onClickIcon2: () -> Unit,
    onClickIconFAB: () -> Unit
) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { onClickIcon1() }) {
                Icon(Icons.Filled.Check, contentDescription = "Localized description")
            }
            IconButton(onClick = { onClickIcon2() }) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Localized description",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClickIconFAB() },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }
    )
}