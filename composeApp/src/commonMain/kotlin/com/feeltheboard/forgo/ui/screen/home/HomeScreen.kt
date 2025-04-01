package com.feeltheboard.forgo.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.feeltheboard.forgo.ui.screen.task.TaskScreen
import com.feeltheboard.forgo.ui.screen.task.components.DisplayTasks
import forgo.composeapp.generated.resources.Res
import forgo.composeapp.generated.resources.add_new_task
import forgo.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

class HomeScreen() : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<HomeViewModel>()
        val activeTasks by viewModel.activeTasks.collectAsState()
        val completedTasks by viewModel.completedTasks.collectAsState()

        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(Res.string.app_name)) }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navigator.push(TaskScreen(navigator = navigator)) },
                    shape = RoundedCornerShape(size = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(Res.string.add_new_task)
                    )
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp)
                    .padding(
                        top = padding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding()
                    )
            ) {
                /**
                 * Display active tasks
                 * **/
                DisplayTasks(
                    modifier = Modifier.weight(1f),
                    tasks = activeTasks,
                    onSelect = { navigator.push(TaskScreen(it, navigator)) },
                    onDelete = { viewModel.deleteTask(it) },
                    onComplete = { viewModel.updateTaskStatus(it, true) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                /**
                 * Display completed tasks
                 * */
                DisplayTasks(
                    modifier = Modifier.weight(1f),
                    tasks = completedTasks,
                    showActive = false,
                    onSelect = { navigator.push(TaskScreen(it, navigator)) },
                    onDelete = { viewModel.deleteTask(it) },
                    onComplete = { viewModel.updateTaskStatus(it, false) }
                )
            }
        }
    }
}