package com.feeltheboard.forgo.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feeltheboard.forgo.data.repository.ForgoRepository
import com.feeltheboard.forgo.domain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val forgoRepository: ForgoRepository
): ViewModel() {

    private var _sortedByFavorite = MutableStateFlow(false)
    val sortedByFavorite: StateFlow<Boolean> = _sortedByFavorite

    private var _tasks = MutableStateFlow<Flow<List<Task>>>(emptyFlow())
    val tasks = _tasks.asStateFlow()

    init {
        viewModelScope.launch {
            getAllTasks()
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            forgoRepository.deleteTask(task)
            getAllTasks()
        }
    }

    private fun getAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            _tasks.value = forgoRepository.getAllTasks()
        }
    }

    fun getTaskByTitle(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            forgoRepository.getTaskByTitle(title)
        }
    }
}