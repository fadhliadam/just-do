package com.adam.justdo.domain.usecase

import com.adam.justdo.data.local.entity.Count
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import com.adam.justdo.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Interactor @Inject constructor(
    private val iRepository: IRepository
) : UseCase {
    override fun getAllTask(): Flow<List<Task>> = iRepository.getAllTask()

    override fun getTaskByGroupId(groupId: Int): Flow<List<Task>> =
        iRepository.getTaskByGroupId(groupId)

    override fun getTaskByImportantOrDueDate(
        isImportant: Int?,
        dueDate: String?
    ): Flow<List<Task>> =
        iRepository.getTaskByImportantOrDueDate(isImportant, dueDate)

    override fun getAllGroup(): Flow<List<Group>> = iRepository.getAllGroup()
    override fun getCount(): Flow<Count> = iRepository.getCount()

    override suspend fun upsertTask(task: Task) = iRepository.upsertTask(task)

    override suspend fun upsertGroup(group: Group) = iRepository.upsertGroup(group)

    override suspend fun updateImportant(id: Int, isImportant: Boolean) =
        iRepository.updateImportant(id, isImportant)

    override suspend fun updateCompleted(id: Int, isCompleted: Boolean) =
        iRepository.updateCompleted(id, isCompleted)

    override suspend fun deleteCompletedAllOrByGroupId(groupId: Int?) =
        iRepository.deleteCompletedAllOrByGroupId(groupId)

    override suspend fun deleteCompletedImportantOrDueDate(isImportant: Int?, dueDate: String?) =
        iRepository.deleteCompletedImportantOrDueDate(isImportant, dueDate)

    override suspend fun deleteTask(task: Task) = iRepository.deleteTask(task)

    override suspend fun deleteGroup(group: Group) = iRepository.deleteGroup(group)
}