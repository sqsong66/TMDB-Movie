package com.tmdb.movie.ext

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Double.formatWithCommasAndDecimals(decimalPlaces: Int): String {
    if (this == 0.0) return "0.00"
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()) as DecimalFormat
    val pattern = "#,###." + "0".repeat(decimalPlaces)
    formatter.applyPattern(pattern)
    return formatter.format(this)
}

fun Float.formatWithCommasAndDecimals(decimalPlaces: Int): String {
    if (this == 0.0f) return "0.00"
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()) as DecimalFormat
    val pattern = "#,###." + "0".repeat(decimalPlaces)
    formatter.applyPattern(pattern)
    return formatter.format(this)
}