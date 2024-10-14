package com.example.myreddit.DataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class DataModel(
    @PrimaryKey val id: String,
    val text: String,
    var imageUrl: String?,
    val nickname: String,
    val count_comments: Int,
    val timestamp_post: Long,
    var avatarUrl: String?
)