package com.feeltheboard.forgo.domain.model


data class TaskState(
    val title: String = "",
    val description: String = "",
    val completed: Boolean = false
)
