package com.feeltheboard.forgo.domain.model


data class TaskState(
    var title: String = "",
    var description: String = "",
    var completed: Boolean = false
)
