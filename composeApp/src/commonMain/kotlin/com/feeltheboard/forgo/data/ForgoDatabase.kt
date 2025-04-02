package com.feeltheboard.forgo.data

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.feeltheboard.forgo.domain.model.Tag
import com.feeltheboard.forgo.domain.model.Task
import com.feeltheboard.forgo.util.DateTimeConverters


@Database(
    entities = [Task::class, Tag::class],
    version = 8,
    exportSchema = true
)
@TypeConverters(DateTimeConverters::class)
@ConstructedBy(ForgoDatabaseConstructor::class)
abstract class ForgoDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun tagDao(): TagDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
// The idea to suppress the error is basically because room will build under the hood.
expect object ForgoDatabaseConstructor : RoomDatabaseConstructor<ForgoDatabase> {
    override fun initialize(): ForgoDatabase
}