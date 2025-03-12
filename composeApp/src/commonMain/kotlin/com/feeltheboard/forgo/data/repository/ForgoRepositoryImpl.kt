package com.feeltheboard.forgo.data.repository

import com.feeltheboard.forgo.data.TaskDao
import com.feeltheboard.forgo.domain.model.Task
import kotlinx.coroutines.flow.Flow

class ForgoRepositoryImpl(
    private val taskDao: TaskDao
) : ForgoRepository {
    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    override suspend fun getTaskByTitle(title: String): Task {
        return taskDao.getTaskByTitle(title)
    }

    override suspend fun getTaskById(id: Int): Task {
        return taskDao.getTaskById(id)
    }
}