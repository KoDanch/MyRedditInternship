package com.example.myreddit.API.APIManager

import android.util.Log
import com.example.myreddit.API.Instance
import com.example.myreddit.DataModel.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class APIDataLoader {

    suspend fun fetchTopPosts(): List<DataModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = Instance.api.getTopPostReddit(
                    limit = 10,
                    intervalPost = "week",
                    sortPost = "top"
                )

                response.data.children.map { post ->
                    val subredditName = post.data.subreddit_name_prefixed

                    DataModel(
                        null.toString(),
                        post.data.title,
                        null,
                        subredditName,
                        post.data.num_comments,
                        post.data.created_utc,
                        null
                    )
                }
            } catch (e: Exception) {
                Log.e("fetchTopPosts()", "Error: ${e.message}")
                emptyList()
            }
        }
    }
}