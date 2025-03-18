package com.feeltheboard.forgo.ui.screen.task

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.feeltheboard.forgo.data.repository.ForgoRepository
import com.feeltheboard.forgo.domain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(
    private val forgoRepository: ForgoRepository
): ScreenModel {
    private var title = mutableStateOf("")

    fun insertTask(task: Task) {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                if (title.value.isNotEmpty()) {
                    val newTask = Task(
                        title = title.value
                    )
                    forgoRepository.insertTask(newTask)
                } else {
                    println("Title is empty")
                }
            } catch (e: Exception) {
                println("Error inserting Task: ${e.message}")
            }
        }
    }

    fun deleteTask(task: Task) {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                val selectedTask = withContext(Dispatchers.IO) {
                    forgoRepository.getTaskById(task.id)
                }
                if (selectedTask != null) {
                    withContext(Dispatchers.IO) {
                        forgoRepository.deleteTask(selectedTask)
                    }
                } else {
                    println("Task with ID ${task.id} not found")
                }
            } catch (e: Exception) {
                println("Error deleting task with ID ${task.id}: ${e.message}")
            }
        }
    }

    fun updateTask(task: Task) {
        screenModelScope.launch(Dispatchers.IO) {
            forgoRepository.updateTask(
                task.copy(
                    title = title.value
                )
            )
        }
    }
}