package com.adam.justdo.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adam.justdo.data.local.entity.Group
import com.adam.justdo.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationVM @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _groupFlow = MutableStateFlow<List<Group>?>(null)
    val groupFlow: StateFlow<List<Group>?> = _groupFlow

    fun getAllGroup() = viewModelScope.launch {
        useCase.getAllGroup().collect {
            _groupFlow.value = it
        }
    }
}