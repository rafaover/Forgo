package com.feeltheboard.forgo.ui.screen.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.feeltheboard.forgo.data.repository.ForgoRepository
import com.feeltheboard.forgo.domain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val forgoRepository: ForgoRepository,
): ScreenModel {

    private val _sortedByFavorite = MutableStateFlow(false)
    val sortedByFavorite: StateFlow<Boolean> = _sortedByFavorite.asStateFlow()

    private val _activeTasks =
        MutableStateFlow<List<Task>>(emptyList())
    val activeTasks = _activeTasks.asStateFlow()

    private val _completedTasks =
        MutableStateFlow<List<Task>>(emptyList())
    val completedTasks = _completedTasks.asStateFlow()

    init {
        screenModelScope.launch {
            forgoRepository.getActiveTasks().collect {
                _activeTasks.value = it
            }
            forgoRepository.getCompletedTasks().collect {
                _completedTasks.value = it
            }
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
        screenModelScope.launch(Dispatchers.IO) {
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

    fun changeTaskStatus(task: Task, completed: Boolean) {
        screenModelScope.launch(Dispatchers.IO) {
            forgoRepository.updateTask(
                task.copy(
                    completed = completed
                )
            )
        }
        getAllTasks()
    }

    private fun getActiveTasks() {
        screenModelScope.launch(Dispatchers.IO) {
            forgoRepository.getActiveTasks()
        }
    }

    private fun getCompletedTasks() {
        screenModelScope.launch(Dispatchers.IO) {
            forgoRepository.getCompletedTasks()
        }
    }

    private fun getAllTasks() {
        screenModelScope.launch(Dispatchers.IO) {
            getActiveTasks()
            getCompletedTasks()
        }
    }

    fun getTaskByTitle(title: String) {
        screenModelScope.launch(Dispatchers.IO) {
            forgoRepository.getTaskByTitle(title)
        }
    }
}