package ru.mobile.lukslol.service.db.converter

import androidx.room.TypeConverter
import ru.mobile.lukslol.util.DateFormat
import ru.mobile.lukslol.util.parse
import java.util.*

class CalendarConverter {

    @TypeConverter
    fun fromCalendar(calendar: Calendar) = DateFormat.SERVER.format(calendar)

    @TypeConverter
    fun toCalendar(json: String) = json.parse(DateFormat.SERVER, stringTimeZone = TimeZone.getDefault())
}