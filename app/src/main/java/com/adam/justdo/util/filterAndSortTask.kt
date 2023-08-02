package com.adam.justdo.util

import com.adam.justdo.data.local.entity.Task
import com.adam.justdo.ui.navigation.ListType
import java.time.LocalDate

fun filterAndSortTask(listType: ListType, listName: String, listTask: List<Task>): List<Task> {
    return if (listType == ListType.Important && listName == "Today") {
        listTask.filter { it.dueDate != null }.filter { item ->
            LocalDate.parse(item.dueDate.toString()) == LocalDate.now()
        }
    } else if (listType == ListType.Important && listName == "Important") {
        listTask.filter { item -> item.isImportant }.sortedBy { it.dueDate }
    } else if (listType == ListType.Important && listName == "All") {
        listTask
    } else {
        listTask.filter { item -> item.groupName == listName }
    }
}