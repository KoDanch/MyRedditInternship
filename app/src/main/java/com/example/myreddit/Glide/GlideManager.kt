package com.example.myreddit.Glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.myreddit.R

class GlideManager {
    fun loadAvatar(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .circleCrop()
            .placeholder(R.drawable.nonvector_placeholder_avatar_icon)
            .into(imageView)
    }
    fun loadMedia(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .asBitmap()
            .load(imageUrl)
            .placeholder(R.drawable.nonvector_placeholder_loading_media)
            .override(1080, 720)
            .into(imageView)
    }
}