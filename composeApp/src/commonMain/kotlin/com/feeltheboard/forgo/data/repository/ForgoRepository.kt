package com.feeltheboard.forgo.data.repository

import com.feeltheboard.forgo.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface ForgoRepository {
    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun updateTask(task: Task)

    fun getAllTasks(): Flow<List<Task>>

    fun getTasksSortedByCreationDate(): Flow<List<Task>>

    fun getTasksDueBefore(date: LocalDate): Flow<List<Task>>

    fun getTasksWithNoDueDate(): Flow<List<Task>>

    fun getActiveTasks(): Flow<List<Task>>

    fun getCompletedTasks(): Flow<List<Task>>

    suspend fun getTaskByTitle(title: String): Task?

    suspend fun getTaskById(id: Int): Task?
}