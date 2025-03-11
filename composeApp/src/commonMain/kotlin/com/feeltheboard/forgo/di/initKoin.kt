package com.feeltheboard.forgo.di

import org.koin.core.context.startKoin

fun initializeKoin() {
    startKoin {
        modules(platformModule, sharedModule)
    }
}