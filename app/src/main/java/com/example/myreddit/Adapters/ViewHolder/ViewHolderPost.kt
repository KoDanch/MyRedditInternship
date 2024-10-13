package com.example.myreddit.Adapters.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myreddit.R

class ViewHolderPost(view: View) : RecyclerView.ViewHolder(view) {
    val avatar_user: ImageView
    val nickname_user: TextView
    val text_post_recyclerView: TextView
    val post_thumbnail: ImageView
    val timestamp_post: TextView
    val count_comments: TextView

    init {
        avatar_user = view.findViewById(R.id.avatar_user)
        nickname_user = view.findViewById(R.id.nickname_user)
        text_post_recyclerView = view.findViewById(R.id.text_post_recyclerView)
        post_thumbnail = view.findViewById(R.id.post_thumbnail)
        timestamp_post = view.findViewById(R.id.timestamp_post)
        count_comments = view.findViewById(R.id.count_comments)
    }
}