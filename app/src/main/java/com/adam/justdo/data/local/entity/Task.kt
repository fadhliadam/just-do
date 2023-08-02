package com.adam.justdo.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "task", foreignKeys = [
        ForeignKey(
            entity = Group::class,
            childColumns = ["group_id"],
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "due_date") val dueDate: String?,
    @ColumnInfo(name = "group_id", index = true) val groupId: Int? = null,
    @ColumnInfo(name = "is_important") val isImportant: Boolean,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean,
    @ColumnInfo(name = "date_added") val dateAdded: String = LocalDateTime.now().toString(),
)
