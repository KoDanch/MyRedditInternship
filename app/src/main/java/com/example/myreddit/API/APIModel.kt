package com.example.myreddit.API

class APIModel {
    data class APIModelPost(
        val id: String,
        val title: String,
        val subreddit_name_prefixed: String,
        val created_utc: Long,
        val url_overridden_by_dest: String?,
        val thumbnail: String?,
        val media: RedditMedia?,
        val num_comments: Int
    )

    data class APIModelWrap(
        val data: APIModelPost
    )

    data class APIModelData(
        val children: List<APIModelWrap>,
        val after: String
    )

    data class APIReditUserData(
        val icon_img: String?,
        val community_icon: String?
    )

    data class APIModelResponse(
        val data: APIModelData
    )

    data class APIModelAboutResponse(
        var data: APIReditUserData
    )

    data class RedditMedia(
        val reddit_video: RedditVideo?
    )

    data class RedditVideo(
        val scrubber_media_url: String?
    )

}