package com.feeltheboard.forgo.di

import com.feeltheboard.forgo.data.getRoomDatabase
import com.feeltheboard.forgo.data.repository.ForgoRepository
import com.feeltheboard.forgo.data.repository.ForgoRepositoryImpl
import com.feeltheboard.forgo.ui.screen.home.HomeViewModel
import com.feeltheboard.forgo.ui.screen.task.TaskViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    singleOf(::ForgoRepositoryImpl) { bind<ForgoRepository>() }
    singleOf(::getRoomDatabase)
    viewModelOf(::HomeViewModel)
    viewModelOf(::TaskViewModel)
}