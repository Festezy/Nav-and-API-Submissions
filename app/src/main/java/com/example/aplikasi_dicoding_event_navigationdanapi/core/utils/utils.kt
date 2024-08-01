package com.example.aplikasi_dicoding_event_navigationdanapi.core.utils

import android.os.Build
import android.text.Spanned
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun convertStringToFormattedString(inputString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

    val localDateTime = LocalDateTime.parse(inputString, inputFormatter)
    return localDateTime.format(outputFormatter)
}

fun convertHtmlToFormattedString(inputHtml: String): String{
    val formatted = HtmlCompat.fromHtml(
        inputHtml,
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )

    return formatted.toString()
}