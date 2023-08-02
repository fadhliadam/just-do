package com.adam.justdo.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTask(): Flow<List<Task>>

    @Query("SELECT * FROM task where group_name = :groupName")
    fun getTaskByGroupName(groupName: String): Flow<List<Task>>

    @Query("SELECT * FROM groups")
    fun getAllGroupName(): Flow<List<Group>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertGroup(group: Group)

    @Delete
    suspend fun deleteTask(task: Task)

    @Delete
    suspend fun deleteGroup(group: Group)
}