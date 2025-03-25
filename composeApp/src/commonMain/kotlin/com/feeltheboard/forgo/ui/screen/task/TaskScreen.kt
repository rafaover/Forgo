package com.feeltheboard.forgo.ui.screen.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.feeltheboard.forgo.domain.model.Task

class TaskScreen(
    val task: Task? = null,
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<TaskViewModel>()

        LaunchedEffect(Unit) {
            if (task != null) {
                viewModel.updateTitle(task.title)
                viewModel.updateDescription(task.description ?: "")
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if (task == null) "Add a Task" else "Edit Task",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Back Arrow"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            if (task == null) {
                                viewModel.insertTask()
                            } else {
                                viewModel.updateTask(task.id)
                            }
                            navigator.pop()
                        }) {
                            Text("Save")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .semantics { contentDescription = "Adding a Task Screen with form fields" },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                /**
                 * Add a title TextField
                 */
                OutlinedTextField(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Start),
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    singleLine = true,
                    label = { Text("Title") },
                    value = viewModel.titleInput,
                    onValueChange = { viewModel.updateTitle(it) }
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                /**
                 * Add a description TextField
                 */
                OutlinedTextField(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Start),
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    label = { Text("Description") },
                    value = viewModel.descriptionInput,
                    onValueChange = { viewModel.updateDescription(it) }
                )
            }
        }
    }
}