package com.example.myreddit.DataModel

data class DataModel(
    val id: String,
    val text: String,
    var imageUrl: String?,
    val nickname: String,
    val count_comments: Int,
    val timestamp_post: Long,
    var avatarUrl: String?
)