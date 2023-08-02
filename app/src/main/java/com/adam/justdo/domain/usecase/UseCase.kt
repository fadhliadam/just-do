package com.adam.justdo.domain.usecase

import com.adam.justdo.data.local.entity.Count
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getAllTask(): Flow<List<Task>>
    fun getTaskByGroupId(groupId: Int): Flow<List<Task>>
    fun getTaskByImportantOrDueDate(isImportant: Int?, dueDate: String?): Flow<List<Task>>
    fun getAllGroup(): Flow<List<Group>>
    fun getCount(): Flow<Count>
    suspend fun upsertTask(task: Task)
    suspend fun upsertGroup(group: Group)
    suspend fun updateImportant(id: Int, isImportant: Boolean)
    suspend fun updateCompleted(id: Int, isCompleted: Boolean)
    suspend fun deleteCompletedAllOrByGroupId(groupId: Int?)
    suspend fun deleteCompletedImportantOrDueDate(
        isImportant: Int?,
        dueDate: String?
    )

    suspend fun deleteTask(task: Task)
    suspend fun deleteGroup(group: Group)
}