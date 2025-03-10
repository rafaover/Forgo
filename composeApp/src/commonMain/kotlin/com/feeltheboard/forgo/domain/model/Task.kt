package com.feeltheboard.forgo.domain.model

import kotlinx.datetime.LocalDateTime

data class Task(
    val id: String,
    val title: String,
    val dueDate: LocalDateTime,
    val location: String
)