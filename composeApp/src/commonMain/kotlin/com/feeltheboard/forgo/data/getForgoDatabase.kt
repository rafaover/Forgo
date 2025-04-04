package com.feeltheboard.forgo.data

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

fun getRoomDatabase(
    builder: RoomDatabase.Builder<ForgoDatabase>
): ForgoDatabase {

    val database = builder
//        .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true)
        .fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()

    prepopulateDatabase(database)

    return database
}