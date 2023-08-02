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

    private val _groupFlow = MutableStateFlow<List<Group>?>(null)
    val groupFlow: StateFlow<List<Group>?> = _groupFlow

    fun getAllTasks() = viewModelScope.launch {
        useCase.getAllTask().collect {
            _taskFlow.value = it
        }
    }

    fun getTaskByGroupName(groupName: String) = viewModelScope.launch {
        useCase.getTaskByGroupName(groupName).collect {
            _taskFlow.value = it
        }
    }

    fun getGroupByName(groupName: String) = viewModelScope.launch {
        useCase.getGroupByName(groupName).collect {
            _groupFlow.value = it
        }
    }

    fun renameGroup(group: Group) = viewModelScope.launch {
        useCase.upsertGroup(group)
    }

    fun deleteGroup(group: Group) = viewModelScope.launch {
        useCase.deleteGroup(group)
    }
}