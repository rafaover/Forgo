package com.feeltheboard.forgo.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feeltheboard.forgo.data.repository.ForgoRepository
import com.feeltheboard.forgo.domain.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val forgoRepository: ForgoRepository,
    private val viewModelScope: CoroutineScope
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

    /**
     * withContext(Dispatchers.IO): Used withContext(Dispatchers.IO) to ensure that both database
     * operations (getTaskById and deleteTask) are executed on the IO dispatcher. While you were
     * already launching on Dispatchers.IO in the initial launch block, you should also assure
     * that forgoRepository functions should be run on the IO thread. This is important because
     * coroutines can execute sequential code on different threads, this assures all of your db
     * code is executed on the same thread.
     */

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val taskToDelete = withContext(Dispatchers.IO) {
                    forgoRepository.getTaskById(task.id)
                }
                if (taskToDelete != null) {
                    withContext(Dispatchers.IO) {
                    forgoRepository.deleteTask(taskToDelete)
                    }
                } else {
                    println("Task with ID ${task.id} not found")
                }
            } catch (e: Exception) {
                println("Error deleting task with ID ${task.id}: ${e.message}")
            }
        }
        getAllTasks()
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