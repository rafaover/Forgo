package com.feeltheboard.forgo

import android.app.Application
import com.feeltheboard.forgo.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initializeKoin {
            androidContext(this@MainApplication)
        }
    }
}