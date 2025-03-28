package com.feeltheboard.forgo.util

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

class DateTimeConverters {
    @TypeConverter
    fun fromInstant(instant: Instant?): Long? {
        return instant?.toEpochMilliseconds()
    }

    @TypeConverter
    fun toInstant(epochMillis: Long?): Instant? {
        return epochMillis?.let { Instant.fromEpochMilliseconds(it) }
    }

    @TypeConverter
    fun fromLocalDate(localDate: LocalDate?): String? {
        return localDate?.toString()
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it) }
    }
}