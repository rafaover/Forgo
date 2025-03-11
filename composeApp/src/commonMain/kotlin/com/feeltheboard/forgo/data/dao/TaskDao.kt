package com.feeltheboard.forgo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.feeltheboard.forgo.data.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<Task>

    // This query the title could be partially matched
    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :title || '%'")
    suspend fun getByTitle(title: String): Task


}