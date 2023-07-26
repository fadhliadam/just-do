package com.adam.justdo.ui.component.group

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FolderDelete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreenModalBottomSheet(
    isOptional: Boolean,
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        windowInsets = WindowInsets.navigationBars,
        onDismissRequest = { onDismissRequest() }
    ) {
        val menuButtonList = listOf(
            MenuButtonItem("rm", Icons.Default.Edit, Color.Blue, "Rename list"),
            MenuButtonItem("del", Icons.Default.FolderDelete, Color.Red, "Delete list"),
            MenuButtonItem("deldn", Icons.Default.Delete, Color.Red, "Delete all completed tasks")
        )
        LazyColumn(
            content = {
                items(menuButtonList) {
                    if (!isOptional && it.id == "del") {
                        Box {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp, horizontal = 14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = it.icon,
                                    contentDescription = "${it.title} icon",
                                    tint = Color.Gray
                                )
                                Column(Modifier.padding(horizontal = 12.dp)) {
                                    Text(
                                        text = it.title,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "Default list can't be deleted",
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    } else {
                        Box(modifier = Modifier.clickable {
                            when(it.id) {
                                "rm" -> { /*TODO*/ }
                                "del" -> { /*TODO*/ }
                                "deldn" -> { /*TODO*/ }
                            }
                        }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = it.icon,
                                    contentDescription = "${it.title} icon",
                                    tint = it.iconColor
                                )
                                Text(
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                    text = it.title,
                                    style = MaterialTheme.typography.titleLarge,
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}