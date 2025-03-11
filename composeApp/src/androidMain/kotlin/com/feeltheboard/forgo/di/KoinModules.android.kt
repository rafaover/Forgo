package com.feeltheboard.forgo.di

import com.feeltheboard.forgo.data.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module


actual val platformModule: Module = module {
    single { getDatabaseBuilder(ctx = get()) }
}