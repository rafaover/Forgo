package com.feeltheboard.forgo.data

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.feeltheboard.forgo.domain.model.Task


@Database(entities = [Task::class], version = 1, exportSchema = true)
@ConstructedBy(ForgoDatabaseConstructor::class)
abstract class ForgoDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object ForgoDatabaseConstructor : RoomDatabaseConstructor<ForgoDatabase> {
    override fun initialize(): ForgoDatabase
}