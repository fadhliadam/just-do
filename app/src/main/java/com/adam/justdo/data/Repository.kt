package com.adam.justdo.data

import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import com.adam.justdo.data.local.room.TaskDatabase
import com.adam.justdo.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val taskDatabase: TaskDatabase
) : IRepository {
    override fun getAllTask(): Flow<List<Task>> = taskDatabase.taskDao().getAllTask()

    override fun getTaskByGroupId(groupId: Int): Flow<List<Task>> =
        taskDatabase.taskDao().getTaskByGroupId(groupId)

    override fun getAllGroup(): Flow<List<Group>> = taskDatabase.taskDao().getAllGroup()

    override suspend fun upsertTask(task: Task) = taskDatabase.taskDao().upsertTask(task)

    override suspend fun upsertGroup(group: Group) = taskDatabase.taskDao().upsertGroup(group)

    override suspend fun updateImportant(id: Int, isImportant: Boolean) =
        taskDatabase.taskDao().updateImportant(id, isImportant)

    override suspend fun updateCompleted(id: Int, isCompleted: Boolean) =
        taskDatabase.taskDao().updateCompleted(id, isCompleted)

    override suspend fun deleteTask(task: Task) = taskDatabase.taskDao().deleteTask(task)

    override suspend fun deleteGroup(group: Group) {
        taskDatabase.taskDao().deleteGroup(group)
    }

}