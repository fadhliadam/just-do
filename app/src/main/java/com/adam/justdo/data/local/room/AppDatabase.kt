package com.adam.justdo.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adam.justdo.data.local.entity.GroupEntity
import com.adam.justdo.data.local.entity.TaskEntity

@Database(entities = [GroupEntity::class,TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}