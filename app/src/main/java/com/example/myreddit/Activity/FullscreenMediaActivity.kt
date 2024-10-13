package com.example.myreddit.Activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myreddit.Glide.GlideManager
import com.example.myreddit.R

class FullscreenMediaActivity : ComponentActivity() {

    private val glide = GlideManager()
    private lateinit var imageUrl: String
    private lateinit var close_imageButton: ImageButton
    private lateinit var fullscreen_imageView: ImageView
    private lateinit var save_button: Button

    companion object {
        private const val REQUEST_CODE: Int = 8743

        fun getRequestCode(): Int {
            return REQUEST_CODE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_fullscreenmedia_activity)
        imageUrl = intent.getStringExtra("IMAGE_URL").toString()
        initUiComponents()
        getFullscreenMedia()
        setActionCloseButton()
        setActionSaveButton()
    }

    private fun initUiComponents() {
        close_imageButton = findViewById(R.id.close_imageButton)
        fullscreen_imageView = findViewById(R.id.body_fullscreen_imageView)
        save_button = findViewById(R.id.save_button)
    }

    private fun getFullscreenMedia() {
        glide.loadMedia(fullscreen_imageView, imageUrl)
    }

    private fun setActionCloseButton() {
        close_imageButton.setOnClickListener {
            finish()
        }
    }

    private fun setActionSaveButton() {
        save_button.setOnClickListener {

            if (checkPermission()) {
                savePhoto()
            } else {
                if (checkAndroidVersion()) {
                    savePhoto()
                } else {
                    Toast.makeText(this, "[Rejected]: No access to the gallery", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun checkPermission(): Boolean {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
            || (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED)
        ) {

            ActivityCompat.requestPermissions(
                this,
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
        glide.saveImage(this, imageUrl)
    }

}