package com.feeltheboard.forgo.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes

fun initializeKoin(
    config : KoinAppDeclaration? = null
) {
    startKoin {
        printLogger()
        includes(config)
        modules(platformModule, sharedModule)
    }
}