package com.example.myreddit.Adapters.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myreddit.R

class ViewHolderPost(view: View) : RecyclerView.ViewHolder(view) {
    val nickname_user: TextView
    val text_post_recyclerView: TextView
    val count_comments: TextView

    init {
        nickname_user = view.findViewById(R.id.nickname_user)
        text_post_recyclerView = view.findViewById(R.id.text_post_recyclerView)
        count_comments = view.findViewById(R.id.count_comments)
    }
}