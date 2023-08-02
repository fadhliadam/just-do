package com.adam.justdo.data.local

import com.adam.justdo.data.local.entity.Task
import java.time.LocalDate
import java.time.LocalDateTime

object TaskDummy {
    var taskDummy = listOf(
        Task(
            id = 0,
            title = "Lorem Ipsum",
            description = "",
            dueDate = LocalDate.now().plusDays(3).toString(),
            groupName = "",
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
        Task(
            id = 0,
            title = "Lorem Ipsum",
            description = "",
            dueDate = LocalDate.now().plusDays(3).toString(),
            groupName = "",
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
        Task(
            id = 0,
            title = "Lorem Ipsum",
            description = "",
            dueDate = null,
            groupName = "",
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
        Task(
            id = 0,
            title = "Lorem Ipsum",
            description = "",
            dueDate = LocalDate.now().toString(),
            groupName = "",
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
        Task(
            id = 0,
            title = "Lorem Ipsum",
            description = "",
            dueDate = LocalDate.now().plusDays(1).toString(),
            groupName = "",
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
        Task(
            id = 0,
            title = "Lorem Ipsum",
            description = "",
            dueDate = LocalDate.now().plusMonths(3).toString(),
            groupName = "",
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
    )
}