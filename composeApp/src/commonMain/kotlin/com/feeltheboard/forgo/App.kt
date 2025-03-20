package com.feeltheboard.forgo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.feeltheboard.forgo.ui.screen.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {

    MaterialTheme {
        KoinContext {
            Navigator(HomeScreen())
        }
    }
}