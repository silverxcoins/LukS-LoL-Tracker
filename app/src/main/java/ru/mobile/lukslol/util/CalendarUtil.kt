package ru.mobile.lukslol.util

import java.text.SimpleDateFormat
import java.util.*

private val ZERO_TIMEZONE = TimeZone.getTimeZone("GMT+0")

fun String.parse(dateFormat: DateFormat,
                 stringTimeZone: TimeZone = ZERO_TIMEZONE,
                 calendarTimeZone: TimeZone = TimeZone.getDefault()): Calendar {
    val correctSource = replace("Z", "+0000")
    val format = SimpleDateFormat(dateFormat.format, Locale.getDefault()).apply { timeZone = stringTimeZone }
    return Calendar.getInstance().apply {
        timeZone = calendarTimeZone
        timeInMillis = format.parse(correctSource)!!.time
    }
}

enum class DateFormat(val format: String) {
    SERVER("yyyy-MM-dd'T'HH:mm:ss.SSSZ"), // 2020-03-13T19:20:30.045+0100
    DDMMMM("d MMMM"); // 13 марта

    fun format(calendar: Calendar): String {
        return SimpleDateFormat(this.format, Locale.getDefault())
            .apply { this.timeZone = calendar.timeZone }
            .format(calendar.time)
    }
}