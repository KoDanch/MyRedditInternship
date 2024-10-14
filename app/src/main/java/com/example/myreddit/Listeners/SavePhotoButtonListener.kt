package com.example.myreddit.Listeners

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myreddit.Activity.FullscreenMediaActivity.Companion.getRequestCode
import com.example.myreddit.Glide.GlideManager

class SavePhotoButtonListener(private val context: Context, private val imageUrl: String) :
    View.OnClickListener {
    private val glide = GlideManager()
    override fun onClick(p0: View?) {
        if (checkPermission()) {
            savePhoto()
        } else {
            if (checkAndroidVersion()) {
                savePhoto()
            } else {
                Toast.makeText(context, "[Rejected]: No access to the gallery", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun checkPermission(): Boolean {

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
            || (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED)
        ) {

            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                getRequestCode()
            )
            return false

        }
        return true
    }

    private fun checkAndroidVersion(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return true
        }
        return false
    }

    private fun savePhoto() {
        glide.saveImage(context, imageUrl)
    }
}