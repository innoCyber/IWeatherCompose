package com.example.iweathercompose.utils

import androidx.compose.ui.text.intl.Locale
import java.text.SimpleDateFormat
import java.util.Date

fun formattedDate(date: Int?, pattern: String = "dd/MM/yyyy"): String {
    return SimpleDateFormat(pattern, java.util.Locale.getDefault()).format(Date(date?.toLong()?.times(1000) ?: 0))
}

fun getIconUrl(iconName: String): String = "https://openweathermap.org/img/wn/$iconName@2x.png"