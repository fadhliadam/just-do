package com.adam.justdo.domain.usecase

import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import com.adam.justdo.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Interactor @Inject constructor(
    private val iRepository: IRepository
) : UseCase {
    override fun getAllTask(): Flow<List<Task>> = iRepository.getAllTask()

    override fun getTaskByGroupName(groupName: String): Flow<List<Task>> =
        iRepository.getTaskByGroupName(groupName)

    override fun getAllGroup(): Flow<List<Group>> = iRepository.getAllGroup()

    override suspend fun upsertTask(task: Task) = iRepository.upsertTask(task)

    override suspend fun upsertGroup(group: Group) = iRepository.upsertGroup(group)

    override suspend fun deleteTask(task: Task) = iRepository.deleteTask(task)

    override suspend fun deleteGroup(group: Group) = iRepository.deleteGroup(group)
}