package com.rayray.madlevel4task2.helper

import androidx.room.TypeConverter
import java.util.*

/**
 * @author Raymond Chang
 *
 * Storing object references in Room is not possible. But it can when you use Converters
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