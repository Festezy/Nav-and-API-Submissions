package com.example.aplikasi_dicoding_event_navigationdanapi.core.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.text.HtmlCompat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun convertStringToFormattedString(inputString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

    val localDateTime = LocalDateTime.parse(inputString, inputFormatter)
    return localDateTime.format(outputFormatter)
}

fun isDatePassed(dateString: String): Boolean {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    dateFormat.isLenient = false

    try {
        val inputDate = dateFormat.parse(dateString) ?: return false
        val currentDate = Date()
        return inputDate.before(currentDate)
    } catch (e: Exception) {
        return false
    }
}

fun convertHtmlToFormattedString(inputHtml: String): String {
    val formatted = HtmlCompat.fromHtml(
        inputHtml,
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )

    return formatted.toString()
}

fun gotoUrl(context: Context, url: String?) {
    val uri: Uri = Uri.parse(url)
    context.startActivity(Intent(Intent.ACTION_VIEW, uri))
}