package com.feeltheboard.forgo.ui.screen.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.feeltheboard.forgo.domain.model.Task
import com.feeltheboard.forgo.ui.components.TaskView

@Composable
fun DisplayTasks(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    showActive: Boolean = true,
    onSelect: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    onComplete: (Task) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    var taskToDelete: Task? by remember { mutableStateOf(null) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = if (showActive) "Active Tasks" else "Completed Tasks",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.padding(horizontal = 24.dp),
            userScrollEnabled = true
        ) {
            itemsIndexed(tasks) { _, task ->
                TaskView(
                    task = task,
                    showActive = showActive,
                    onSelect = { onSelect(task) },
                    onComplete = { onComplete( task) },
                    onDelete = {
                        taskToDelete = task
                        showDialog = true
                    }
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            title = {
                Text(
                    text = "Remove Task",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to remove the task '${taskToDelete!!.title}'?",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            },
            confirmButton = {
                Button(onClick = {
                    onDelete.invoke(taskToDelete!!)
                    showDialog = false
                    taskToDelete = null
                }) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    taskToDelete = null
                    showDialog = false
                }) {
                    Text(text = "Cancel")
                }
            },
            onDismissRequest = {
                taskToDelete = null
                showDialog = false
            }
        )
    }
}