package com.example.myreddit.API.APIManager

import android.util.Log
import com.example.myreddit.API.Instance
import com.example.myreddit.DataModel.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIDataLoader {
    private  val apiLoadMedia = APILoadMedia()

    suspend fun fetchTopPosts(): List<DataModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = Instance.api.getTopPostReddit(
                    limit = 10,
                    intervalPost = "week",
                    sortPost = "top"
                )

               val posts = response.data.children.map { post ->
                    val subredditName = post.data.subreddit_name_prefixed
                    val textPost = post.data.title
                    val countComments = post.data.num_comments
                    val timePostCreated = post.data.created_utc

                    DataModel(
                        null.toString(),
                        textPost,
                        null,
                        subredditName,
                        countComments,
                        timePostCreated,
                        null
                    ).also { dataModel ->
                        launch(Dispatchers.IO) {
                            val fetchAvatar = apiLoadMedia.getUserAvatar(subredditName)
                            withContext(Dispatchers.Main) {
                                dataModel.avatarUrl = fetchAvatar
                            }
                        }
                        launch(Dispatchers.IO) {
                            val fetchMedia = apiLoadMedia.getPostMedia(
                                post.data.url_overridden_by_dest,
                                post.data.media?.reddit_video?.scrubber_media_url,
                                post.data.thumbnail)
                            withContext(Dispatchers.Main) {
                                dataModel.imageUrl = fetchMedia
                            }
                        }
                    }
                }
                posts
            } catch (e: Exception) {
                Log.e("fetchTopPosts()", "Error: ${e.message}")
                emptyList()
            }
        }
    }
}