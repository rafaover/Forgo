package com.feeltheboard.forgo.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "description")
    val description: String = "",

    @ColumnInfo(name = "completed")
    val completed: Boolean = false,

    @ColumnInfo(name = "due_date")
    val dueDate: LocalDate? = null,

    @ColumnInfo(name = "tags")
    val tags: List<Tag> = emptyList(),

    @ColumnInfo(name = "created_at")
    val createdAt: Instant = Clock.System.now(),
)