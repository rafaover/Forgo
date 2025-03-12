package com.feeltheboard.forgo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    MaterialTheme {
        // ?? KoinApplication ??
        KoinContext {
            TODO("Introduce composable content here")
        }
    }
}