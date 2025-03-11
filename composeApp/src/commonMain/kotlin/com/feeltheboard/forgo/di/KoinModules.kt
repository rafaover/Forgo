package com.feeltheboard.forgo.di

import com.feeltheboard.forgo.data.getRoomDatabase
import com.feeltheboard.forgo.data.repository.ForgoRepository
import com.feeltheboard.forgo.data.repository.ForgoRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single<ForgoRepository> {
        ForgoRepositoryImpl(taskDao = get())
    }
    single { getRoomDatabase(get()) }
}