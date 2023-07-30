package com.adam.justdo.data.local

import com.adam.justdo.domain.model.Task
import java.sql.Date

object TaskDummy {
    var taskDummy = listOf(
        Task(
            title = "Lorem Ipsum",
            description = "",
            dueDate = Date.valueOf("2023-07-31"),
            listGroup = "",
            isImportant = true,
            dateAdded = Date.valueOf("2023-07-30"),
            isCompleted = false,
        ),
        Task(
            title = "Lorem Ipsum",
            description = "",
            dueDate = Date.valueOf("2023-08-31"),
            listGroup = "",
            isImportant = true,
            dateAdded = Date.valueOf("2023-07-30"),
            isCompleted = false,
        ),
        Task(
            title = "Lorem Ipsum",
            description = "Lorem Ipsum Ahoy",
            dueDate = Date.valueOf("2023-07-31"),
            listGroup = "",
            isImportant = true,
            dateAdded = Date.valueOf("2023-07-30"),
            isCompleted = false,
        ),
        Task(
            title = "Lorem Ipsum",
            description = "",
            dueDate = Date.valueOf("2023-08-02"),
            listGroup = "",
            isImportant = false,
            dateAdded = Date.valueOf("2023-07-30"),
            isCompleted = true,
        ),
        Task(
            title = "Lorem Ipsum",
            description = "",
            dueDate = Date.valueOf("2023-07-31"),
            listGroup = "",
            isImportant = false,
            dateAdded = Date.valueOf("2023-07-30"),
            isCompleted = false,
        ),
        Task(
            title = "Lorem Ipsum",
            description = "",
            dueDate = Date.valueOf("2023-08-04"),
            listGroup = "",
            isImportant = true,
            dateAdded = Date.valueOf("2023-07-30"),
            isCompleted = false,
        ),
        Task(
            title = "Lorem Ipsum",
            description = "",
            dueDate = Date.valueOf("2023-07-30"),
            listGroup = "",
            isImportant = false,
            dateAdded = Date.valueOf("2023-07-30"),
            isCompleted = false,
        ),
        Task(
            title = "Lorem Ipsum",
            description = "",
            dueDate = Date.valueOf("2023-07-30"),
            listGroup = "",
            isImportant = false,
            dateAdded = Date.valueOf("2023-07-30"),
            isCompleted = false,
        ),
    )
}