package com.feeltheboard.forgo.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initializeKoin(
    config : KoinAppDeclaration? = null
) {
    startKoin {
        printLogger()
        config?.invoke(this)
        modules(appModules())
    }
}