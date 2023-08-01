package com.adam.justdo.ui.component.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.adam.justdo.R
import com.adam.justdo.ui.component.GroupTaskDialog
import com.adam.justdo.ui.navigation.ListType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreActionModalBottomSheet(
    listName: String,
    listType: ListType,
    isListCompletedEmpty: Boolean,
    onRename: (String) -> Unit,
    onDeleteList: () -> Unit,
    onDeleteCompletedTask: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    var openRenameListTask by remember { mutableStateOf(false) }
    var openDeleteListDialog by remember { mutableStateOf(false) }
    var openDeleteAllDialog by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val containerColor =
        if (!isSystemInDarkTheme()) Color.White else MaterialTheme.colorScheme.background

    val menuButtonList = listOf(
        MenuButtonItem("rm", Icons.Default.Edit, Color.Blue, "Rename list"),
        MenuButtonItem("del", Icons.Default.FolderDelete, Color.Red, "Delete list"),
        MenuButtonItem("deldn", Icons.Default.Delete, Color.Red, "Delete all completed tasks")
    )

    ModalBottomSheet(
        containerColor = containerColor,
        sheetState = sheetState,
        dragHandle = {},
        onDismissRequest = { onDismissRequest() }
    ) {
        LazyColumn(
            modifier = Modifier.padding(vertical = 18.dp),
            content = {
                items(menuButtonList) {
                    if (listType != ListType.Optional && (it.id == "del" || it.id == "rm")) {
                        val actionText = if (it.id == "del") "deleted" else "renamed"
                        Box {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 14.dp, horizontal = 18.dp),
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
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "Default list can't be $actionText",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    } else if (isListCompletedEmpty && it.id == "deldn") {
                        Box {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 14.dp, horizontal = 18.dp),
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
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.Gray
                                )
                            }
                        }
                    } else {
                        Box(modifier = Modifier.clickable {
                            when (it.id) {
                                "rm" -> {
                                    openRenameListTask = !openRenameListTask
                                }

                                "del" -> {
                                    openDeleteListDialog = !openDeleteListDialog
                                }

                                "deldn" -> {
                                    openDeleteAllDialog = !openDeleteAllDialog
                                }
                            }
                        }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 14.dp, horizontal = 18.dp),
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
                                    style = MaterialTheme.typography.titleMedium,
                                )
                            }
                        }
                    }
                }
            }
        )
        if (openRenameListTask) {
            GroupTaskDialog(
                value = listName,
                onDismissRequest = { openRenameListTask = false },
                onCancel = { openRenameListTask = false },
                onSave = {
                    onRename(it)
                    openRenameListTask = false
                },
            )
        }
        if (openDeleteListDialog) {
            DeleteDialog(
                title = stringResource(R.string.delete_list),
                description = "\"$listName\" ${stringResource(R.string.delete_list_desc_suffix)}",
                onDelete = { onDeleteList() },
                onDismissRequest = { openDeleteListDialog = false }
            )
        }
        if (openDeleteAllDialog) {
            DeleteDialog(
                title = stringResource(R.string.delete_all_completed_tasks),
                description = stringResource(R.string.delete_all_completed_desc),
                onDelete = { onDeleteCompletedTask() },
                onDismissRequest = { openDeleteAllDialog = false }
            )
        }
    }
}