package com.adam.justdo.domain.repository

import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getAllTask(): Flow<List<Task>>
    fun getTaskByGroupName(groupName: String): Flow<List<Task>>
    fun getAllGroup(): Flow<List<Group>>
    fun getGroupByName(groupName: String): Flow<List<Group>>
    suspend fun upsertTask(task: Task)
    suspend fun upsertGroup(group: Group)
    suspend fun deleteTask(task: Task)
    suspend fun deleteGroup(group: Group)
}