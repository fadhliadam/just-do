package com.adam.justdo.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adam.justdo.data.local.entity.GroupEntity
import com.adam.justdo.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTask(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM `group`")
    fun getAllGroupName(): Flow<List<GroupEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTask(task: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertGroup(group: GroupEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Delete
    suspend fun deleteGroup(group: GroupEntity)
}