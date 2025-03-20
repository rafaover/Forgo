package com.feeltheboard.forgo.data

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Room

fun getDatabaseBuilder(
    ctx: Context
): RoomDatabase.Builder<ForgoDatabase> {

    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("task.db")

    return Room.databaseBuilder<ForgoDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}