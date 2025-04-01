package com.feeltheboard.forgo.ui.screen.task.components

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
import forgo.composeapp.generated.resources.Res
import forgo.composeapp.generated.resources.active_tasks
import forgo.composeapp.generated.resources.cancel
import forgo.composeapp.generated.resources.completed_tasks
import forgo.composeapp.generated.resources.confirm_to_delete_msg
import forgo.composeapp.generated.resources.delete_task
import forgo.composeapp.generated.resources.yes
import org.jetbrains.compose.resources.stringResource

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
            text = if (showActive)
                stringResource(Res.string.active_tasks)
            else
                stringResource(Res.string.completed_tasks),

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
                    text = stringResource(Res.string.delete_task),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            },
            text = {
                Text(
                    text = stringResource(Res.string.confirm_to_delete_msg, taskToDelete!!.title),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            },
            confirmButton = {
                Button(onClick = {
                    onDelete.invoke(taskToDelete!!)
                    showDialog = false
                    taskToDelete = null
                }) {
                    Text(text = stringResource(Res.string.yes))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    taskToDelete = null
                    showDialog = false
                }) {
                    Text(text = stringResource(Res.string.cancel))
                }
            },
            onDismissRequest = {
                taskToDelete = null
                showDialog = false
            }
        )
    }
}