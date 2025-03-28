package com.feeltheboard.forgo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.feeltheboard.forgo.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks ORDER BY created_at DESC")
    fun getTasksSortedByCreationDate(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE due_date <= :date")
    fun getTasksDueBefore(date: LocalDate): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE due_date IS NULL")
    fun getTasksWithNoDueDate(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE completed = false")
    fun getActiveTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE completed = true")
    fun getCompletedTasks(): Flow<List<Task>>

    // This query the title could be partially matched
    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :title || '%'")
    suspend fun getTaskByTitle(title: String): Task

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Int): Task
}