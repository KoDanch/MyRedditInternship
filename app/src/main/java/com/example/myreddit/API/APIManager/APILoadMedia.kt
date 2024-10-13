package com.example.myreddit.API.APIManager

import android.util.Log
import com.example.myreddit.API.Instance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class APILoadMedia {
    suspend fun getUserAvatar(subbreddit_name: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val response = Instance.api.getAboutSubreddit(subbreddit_name)
                if (response.data.icon_img.toString() != "") {
                    response.data.icon_img
                } else {
                    val cutUrl: String = response.data.community_icon.toString()
                    val intStroke: Int = cutUrl.indexOf('?')
                    cutUrl.substring(0, intStroke)
                }
            } catch (e: Exception) {
                Log.e("getUserAvatar()", "Error: ${e.message}")
                ""
            }.toString()
        }
    }

    fun getPostMedia(
        mediaUrlPathOne: String?,
        mediaUrlPathTwo: String?,
        mediaUrlPathThree: String?
    ): String {
        val currentMedia: String
        val regexPhoto = Regex(".*\\.(jpg|jpeg|png|gif|webp)$")

        if (mediaUrlPathOne.toString().matches(regexPhoto)) {
            currentMedia = mediaUrlPathOne.toString()
        } else if (mediaUrlPathTwo != null) {
            currentMedia =
                mediaUrlPathTwo.toString()

        } else {
            currentMedia = mediaUrlPathThree.toString()
        }

        Log.e("MediaURL", currentMedia)

        return currentMedia
    }
}