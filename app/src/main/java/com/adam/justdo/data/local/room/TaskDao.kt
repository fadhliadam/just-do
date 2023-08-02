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
    fun getAllGroup(): Flow<List<Group>>

    @Query("SELECT * FROM groups where group_name = :groupName")
    fun getGroupByName(groupName: String): Flow<List<Group>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertGroup(group: Group)

    @Query("UPDATE task SET is_important=:isImportant WHERE id = :id")
    suspend fun updateImportant(id: Int, isImportant: Boolean)

    @Query("UPDATE task SET is_completed=:isCompleted WHERE id = :id")
    suspend fun updateCompleted(id: Int, isCompleted: Boolean)

    @Query("DELETE FROM task where group_name = :groupName")
    suspend fun deleteTaskByGroupName(groupName: String)

    @Delete
    suspend fun deleteTask(task: Task)

    @Delete
    suspend fun deleteGroup(group: Group)
}