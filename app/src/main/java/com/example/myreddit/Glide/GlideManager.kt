package com.example.myreddit.Glide

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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
            .into(imageView)
    }

    fun saveImage(context: Context, imageUrl: String?) {
        if (imageUrl != null) {

            Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .into(object : CustomTarget<Bitmap>() {

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        val contentValues = ContentValues().apply {
                            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                            if (checkAndroidVersion()) {
                                put(
                                    MediaStore.Images.Media.RELATIVE_PATH,
                                    Environment.DIRECTORY_PICTURES
                                )
                            } else {
                                put(
                                    MediaStore.Images.Media.DATA,
                                    "${Environment.getExternalStorageDirectory()}/${Environment.DIRECTORY_PICTURES}/MyReddit_${System.currentTimeMillis()}.jpg"
                                )
                            }
                        }

                        val uri = context.contentResolver.insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            contentValues
                        )

                        if (uri != null) {
                            try {
                                context.contentResolver.openOutputStream(uri).use { out ->
                                    if (out != null) {
                                        resource.compress(Bitmap.CompressFormat.JPEG, 100, out)
                                        Toast.makeText(
                                            context,
                                            "[Success]: Saved to gallery",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Log.e("SaveImage", "[Error]: Couldn't open output stream")
                                    }
                                }
                            } catch (e: Exception) {
                                Toast.makeText(
                                    context,
                                    "[Error]: Couldn't save :( -> ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
        }
    }

    private fun checkAndroidVersion(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return true
        }
        return false
    }

}