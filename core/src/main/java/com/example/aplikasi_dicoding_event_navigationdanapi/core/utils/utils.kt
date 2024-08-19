package com.example.aplikasi_dicoding_event_navigationdanapi.core.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
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

fun gotoUrl(context: Context, url: String?) {
    val uri: Uri = Uri.parse(url)
    context.startActivity(Intent(Intent.ACTION_VIEW, uri))
}