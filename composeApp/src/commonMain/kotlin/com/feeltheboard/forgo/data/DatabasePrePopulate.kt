package com.feeltheboard.forgo.data

import com.feeltheboard.forgo.domain.model.Tag
import com.feeltheboard.forgo.domain.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

/**
 * Prepopulates the database with default tags only if no tags exist.
 */
fun prepopulateDatabase(database: ForgoDatabase) {
    val tagDao = database.tagDao()
    val taskDao = database.taskDao()

    CoroutineScope(Dispatchers.IO).launch {
        // Check if there are any tags already
        val tagCount = tagDao.getTagCount()

        // Only add default tags if the database is empty
        if (tagCount == 0) {
            val defaultTags = listOf(
                Tag(name = "Family", color = "#FF9800"),  // Orange
                Tag(name = "Work", color = "#2196F3"),    // Blue
                Tag(name = "Random", color = "#9C27B0")   // Purple
            )
            val defaultTask =
                Task(title = "Buy groceries", description = "Buy milk, eggs, and bread", tagId = 1)

            tagDao.insertAll(defaultTags)
            taskDao.insertTask(defaultTask)

        }
    }
}
