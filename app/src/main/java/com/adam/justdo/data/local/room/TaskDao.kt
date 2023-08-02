package com.adam.justdo.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTask(): Flow<List<Task>>

    @Query("SELECT * FROM task where group_id = :groupId")
    fun getTaskByGroupId(groupId: Int): Flow<List<Task>>

    @Query("SELECT * FROM groups")
    fun getAllGroup(): Flow<List<Group>>

    @Upsert
    suspend fun upsertTask(task: Task)

    @Upsert
    suspend fun upsertGroup(group: Group)

    @Query("UPDATE task SET is_important=:isImportant WHERE id = :id")
    suspend fun updateImportant(id: Int, isImportant: Boolean)

    @Query("UPDATE task SET is_completed=:isCompleted WHERE id = :id")
    suspend fun updateCompleted(id: Int, isCompleted: Boolean)

    @Query("DELETE FROM task where group_id = :groupId")
    suspend fun deleteTaskByGroupId(groupId: Int)

    @Delete
    suspend fun deleteTask(task: Task)

    @Delete
    suspend fun deleteGroup(group: Group)
}