package com.example.myreddit.RecyclerViewLoader

import android.content.Context
import com.example.myreddit.R

class DifferenceTimeHelper(private val context: Context) {

    fun getDiffHour(timestamp: Long): String {
        if (timestamp.toString().isNotEmpty()) {
            val currentUnixTime: Long = System.currentTimeMillis() / 1000
            val diffTime = kotlin.math.abs(currentUnixTime - timestamp) / 3600

            val diffHourText = "$diffTime" + " " + context.getString(R.string.timestamp_post)

            return diffHourText
        }

        return ""
    }
}