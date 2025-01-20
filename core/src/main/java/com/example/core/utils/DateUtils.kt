package com.example.core.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {

    private val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    private val outputDateFormat_RU = SimpleDateFormat("dd MMMM yyyy", Locale("ru", "RU"))

    fun formatDateRU(dateString: String?): String {
        return try {
            dateString?.let {
                val date = inputDateFormat.parse(it)
                date?.let { outputDateFormat_RU.format(it) } ?: "Invalid date"
            } ?: "Invalid date"
        } catch (e: Exception) {
            Log.e("Utils", "(DateUtils) Error parsing date: ${e.message}")
            "Invalid date"
        }
    }

}