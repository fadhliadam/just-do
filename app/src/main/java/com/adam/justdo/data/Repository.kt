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

    override fun getTaskByGroupName(groupName: String): Flow<List<Task>> =
        taskDatabase.taskDao().getTaskByGroupName(groupName)


    override fun getAllGroup(): Flow<List<Group>> = taskDatabase.taskDao().getAllGroupName()


    override suspend fun upsertTask(task: Task) = taskDatabase.taskDao().upsertTask(task)


    override suspend fun upsertGroup(group: Group) = taskDatabase.taskDao().upsertGroup(group)

    override suspend fun deleteTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGroup(group: Group) {
        TODO("Not yet implemented")
    }

}