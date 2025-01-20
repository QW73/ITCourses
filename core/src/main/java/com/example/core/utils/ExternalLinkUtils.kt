package com.example.core.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast

fun openExternalLink(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Log.e("Utils", "(OpenExternalLink) Link opening error: ${e.message}")
        Toast.makeText(context, "Browser not found", Toast.LENGTH_SHORT).show()
    }
}