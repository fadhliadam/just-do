package com.adam.justdo.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "due_date") val dueDate: String?,
    @ColumnInfo(name = "group_name") val groupName: String,
    @ColumnInfo(name = "is_important") val isImportant: Boolean,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean,
    @ColumnInfo(name = "date_added") val dateAdded: String,
)
