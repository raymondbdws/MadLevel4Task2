package com.rayray.madlevel4task2.helper

import androidx.room.TypeConverter
import java.util.*

/**
 * @author Raymond Chang
 *
 *
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}