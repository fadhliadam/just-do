package com.adam.justdo.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.adam.justdo.data.local.entity.Count
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTask(): Flow<List<Task>>

    @Query("SELECT * FROM task where group_id = :groupId")
    fun getTaskByGroupId(groupId: Int): Flow<List<Task>>

    @Query("SELECT * FROM task where is_important = :isImportant OR due_date = :dueDate")
    fun getTaskByImportantOrDueDate(isImportant: Int?, dueDate: String?): Flow<List<Task>>

    @Query("SELECT * FROM groups")
    fun getAllGroup(): Flow<List<Group>>

    @Query(
        "SELECT COUNT(*) as all_count, " +
                "SUM(CASE WHEN is_important = 1 THEN 1 ELSE 0 END) as important_count, " +
                "COUNT(DATE(due_date) = DATE('now')) as today_count FROM task WHERE is_completed = 0"
    )
    fun getCount(): Flow<Count>

    @Upsert
    suspend fun upsertTask(task: Task)

    @Upsert
    suspend fun upsertGroup(group: Group)

    @Query("UPDATE task SET is_important=:isImportant WHERE id = :id")
    suspend fun updateImportant(id: Int, isImportant: Boolean)

    @Query("UPDATE task SET is_completed=:isCompleted WHERE id = :id")
    suspend fun updateCompleted(id: Int, isCompleted: Boolean)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task WHERE group_id = :groupId")
    suspend fun deleteTaskByGroupId(groupId: Int)

    @Query("DELETE FROM task WHERE group_id = :groupId OR is_completed = 1")
    suspend fun deleteCompletedAllOrByGroupId(groupId: Int? = null)

    @Query(
        "DELETE FROM task WHERE is_completed = 1 " +
                "AND (is_important = :isImportant OR due_date = :dueDate)"
    )
    suspend fun deleteCompletedImportantOrDueDate(
        isImportant: Int?,
        dueDate: String?
    )

    @Delete
    suspend fun deleteGroup(group: Group)
}