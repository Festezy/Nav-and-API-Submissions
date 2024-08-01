package com.example.aplikasi_dicoding_event_navigationdanapi.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun convertDateToString(date: String): String {
    val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy")
    return date.format(formatter)
}

fun convertStringToFormattedString(inputString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

    val localDateTime = LocalDateTime.parse(inputString, inputFormatter)
    return localDateTime.format(outputFormatter)
}