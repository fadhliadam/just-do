package com.adam.justdo.domain.model

import java.sql.Date


data class Task(
    var title: String,
    var description: String,
    var dueDate: Date?,
    var listGroup: String,
    var isImportant: Boolean,
    var isCompleted: Boolean,
    val dateAdded: Date,
)
