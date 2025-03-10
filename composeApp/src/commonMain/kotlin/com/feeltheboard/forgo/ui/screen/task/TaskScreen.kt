package com.feeltheboard.forgo.ui.screen.task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.datetime.LocalDateTime

@Composable
fun TaskScreen() {
    val title = remember { mutableStateOf(TextFieldValue()) }
    val dueDate = remember { mutableStateOf(LocalDateTime) }
    val location = remember { mutableStateOf(TextFieldValue()) }

    // UI elements for input fields and buttons to add tasks
    // ...
}