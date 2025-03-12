package com.feeltheboard.forgo.di

import com.feeltheboard.forgo.data.getRoomDatabase
import com.feeltheboard.forgo.data.repository.ForgoRepository
import com.feeltheboard.forgo.data.repository.ForgoRepositoryImpl
import com.feeltheboard.forgo.ui.screen.home.HomeViewModel
import com.feeltheboard.forgo.ui.screen.task.TaskViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single<ForgoRepository> { ForgoRepositoryImpl(taskDao = get()) }
    single { getRoomDatabase(get()) }
    viewModel { HomeViewModel(forgoRepository = get()) }
    viewModel { TaskViewModel(forgoRepository = get()) }
}