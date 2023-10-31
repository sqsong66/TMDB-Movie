package com.tmdb.movie.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatLongToString(millis: Long, format: String = "yyyy-MM-dd"): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    try {
        return sdf.format(Date(millis))
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

fun niceDate(timeStr: String?, format: String = "yyyy-MM-dd", dateFormat: Int = DateFormat.SHORT): String? {
    if (timeStr.isNullOrEmpty()) return null

    val sdf = SimpleDateFormat(format, Locale.getDefault())
    try {
        val date = sdf.parse(timeStr)
        return date?.let { DateFormat.getDateInstance(dateFormat).format(it) } ?: ""
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun formatToMonthDay(timeStr: String?, format: String = "yyyy-MM-dd"): String {
    if (timeStr.isNullOrEmpty()) return ""
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    try {
        val date = sdf.parse(timeStr)
        val outputFormat = SimpleDateFormat("MMM dd", Locale.getDefault()) // 使用本地化的日期格式
        return date?.let { outputFormat.format(it) } ?: ""
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

fun formatMinuteToHM(minute: Int): String {
//    val hour = minute / 60
//    val min = minute % 60
//    return if (hour > 0) {
//        "${hour}h ${min}m"
//    } else {
//        "${min}m"
//    }
    return "${minute}m"
}