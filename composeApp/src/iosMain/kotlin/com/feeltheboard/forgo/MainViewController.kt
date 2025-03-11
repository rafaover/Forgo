package com.feeltheboard.forgo

import androidx.compose.ui.window.ComposeUIViewController
import com.feeltheboard.forgo.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) {
    App()
}