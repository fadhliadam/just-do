package com.adam.justdo.ui.component.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adam.justdo.ui.navigation.ListType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreActionModalBottomSheet(
    listType: ListType,
    isListEmpty: Boolean,
    onDismissRequest: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() }
    ) {
        val menuButtonList = listOf(
            MenuButtonItem("rm", Icons.Default.Edit, Color.Blue, "Rename list"),
            MenuButtonItem("del", Icons.Default.FolderDelete, Color.Red, "Delete list"),
            MenuButtonItem("deldn", Icons.Default.Delete, Color.Red, "Delete all completed tasks")
        )
        LazyColumn(
            modifier = Modifier.padding(bottom = 18.dp),
            content = {
                items(menuButtonList) {
                    if (listType != ListType.Optional && (it.id == "del" || it.id == "rm")) {
                        val actionText = if (it.id == "del") "deleted" else "renamed"
                        Box {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
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
                                        text = "Default list can't be $actionText",
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    } else if (isListEmpty && it.id == "deldn") {
                        Box {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = it.icon,
                                    contentDescription = "${it.title} icon",
                                    tint = Color.Gray
                                )
                                Text(
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                    text = it.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Gray
                                )
                            }
                        }
                    } else {
                        Box(modifier = Modifier.clickable {
                            when (it.id) {
                                "rm" -> { /*TODO*/
                                }

                                "del" -> { /*TODO*/
                                }

                                "deldn" -> { /*TODO*/
                                }
                            }
                        }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
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