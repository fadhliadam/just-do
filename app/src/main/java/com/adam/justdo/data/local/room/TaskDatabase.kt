package com.adam.justdo.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task

@Database(entities = [Group::class, Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}