package com.feeltheboard.forgo.ui.screen.task

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.feeltheboard.forgo.data.repository.ForgoRepository
import com.feeltheboard.forgo.domain.model.Task
import com.feeltheboard.forgo.domain.model.TaskState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(
    private val forgoRepository: ForgoRepository
): ScreenModel {

    private val _taskState = MutableStateFlow(TaskState())
    val taskState = _taskState.asStateFlow()

    fun insertTask() {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                if (taskState.value.title.isNotEmpty()) {
                    val newTask = Task(
                        title = taskState.value.title,
                        description = taskState.value.description,
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
                    title = taskState.value.title,
                    description = taskState.value.description,
                    completed = taskState.value.completed
                )
            )
        }
    }
}