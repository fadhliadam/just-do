package com.adam.justdo.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.data.local.entity.Task
import com.adam.justdo.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _taskFlow = MutableStateFlow<List<Task>?>(null)
    val taskFlow: StateFlow<List<Task>?> = _taskFlow

    fun getAllTasks() = viewModelScope.launch {
        useCase.getAllTask().collect {
            _taskFlow.value = it
        }
    }

    fun getTaskByGroupId(groupId: Int) = viewModelScope.launch {
        useCase.getTaskByGroupId(groupId).collect {
            _taskFlow.value = it
        }
    }

    fun getTaskByImportantOrDueDate(isImportant: Int? = null, dueDate: String? = null) =
        viewModelScope.launch {
            useCase.getTaskByImportantOrDueDate(isImportant, dueDate).collect {
                _taskFlow.value = it
            }
        }

    fun upsertTask(task: Task) = viewModelScope.launch {
        useCase.upsertTask(task)
    }

    fun updateImportant(id: Int, isImportant: Boolean) = viewModelScope.launch {
        useCase.updateImportant(id, isImportant)
    }

    fun updateCompleted(id: Int, isCompleted: Boolean) = viewModelScope.launch {
        useCase.updateCompleted(id, isCompleted)
    }

    fun renameGroup(group: Group) = viewModelScope.launch {
        useCase.upsertGroup(group)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        useCase.deleteTask(task)
    }

    fun deleteCompletedAllOrByGroupId(groupId: Int? = null) = viewModelScope.launch {
        useCase.deleteCompletedAllOrByGroupId(groupId)
    }

    fun deleteCompletedImportantOrDueDate(isImportant: Int? = null, dueDate: String? = null) =
        viewModelScope.launch {
            useCase.deleteCompletedImportantOrDueDate(isImportant, dueDate)
        }

    fun deleteGroup(group: Group) = viewModelScope.launch {
        useCase.deleteGroup(group)
    }
}