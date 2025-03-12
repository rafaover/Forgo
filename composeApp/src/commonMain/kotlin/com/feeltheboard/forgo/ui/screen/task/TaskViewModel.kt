package com.feeltheboard.forgo.ui.screen.task

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feeltheboard.forgo.data.repository.ForgoRepository
import com.feeltheboard.forgo.domain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class TaskViewModel(
    private val forgoRepository: ForgoRepository
): ViewModel() {
    private var title = mutableStateOf("")

    fun insertTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (title.value.isNotEmpty()) {
                    val newTask = Task(
                        title = title.value
                    )
                    forgoRepository.insertTask(newTask)
                } else {
                    error("Title is empty")
                }
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            val selectedTask = forgoRepository.getTaskById(task.id)
            forgoRepository.deleteTask(selectedTask)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            forgoRepository.updateTask(
                task.copy(
                    title = title.value
                )
            )
        }
    }
}