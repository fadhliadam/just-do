package com.adam.justdo.domain.usecase

import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getAllTask(): Flow<List<Task>>
    fun getTaskByGroupName(groupName: String): Flow<List<Task>>
    fun getAllGroup(): Flow<List<Group>>
    suspend fun upsertTask(task: Task)
    suspend fun upsertGroup(group: Group)
    suspend fun deleteTask(task: Task)
    suspend fun deleteGroup(group: Group)
}