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
            groupId = 0,
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
        Task(
            id = 0,
            title = "Lorem Ipsum",
            description = "",
            dueDate = LocalDate.now().plusDays(3).toString(),
            groupId = 0,
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
        Task(
            id = 0,
            title = "Lorem Ipsum",
            description = "",
            dueDate = null,
            groupId = 0,
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
        Task(
            id = 0,
            title = "Lorem Ipsum",
            description = "",
            dueDate = LocalDate.now().toString(),
            groupId = 0,
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
        Task(
            id = 0,
            title = "Lorem Ipsum",
            description = "",
            dueDate = LocalDate.now().plusDays(1).toString(),
            groupId = 0,
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
        Task(
            id = 0,
            title = "Lorem Ipsum",
            description = "",
            dueDate = LocalDate.now().plusMonths(3).toString(),
            groupId = 0,
            isImportant = true,
            isCompleted = false,
            dateAdded = LocalDateTime.now().toString(),
        ),
    )
}