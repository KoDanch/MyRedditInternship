package com.example.myreddit.Activity

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myreddit.Database.Database
import com.example.myreddit.Database.DatabaseSingleton
import com.example.myreddit.R
import com.example.myreddit.RecyclerViewLoader.PostRecyclerViewLoader

class MainActivity : ComponentActivity() {
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var progressBar: ProgressBar
    private lateinit var db: Database
    private lateinit var reflesh_button: ImageButton
    private lateinit var sharedPreferences: SharedPreferences

    private var lastSavedPosition: Int = 0
    private var isAtSavedPosition: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main_activity)

        initComponents()
        loadPost()
        setPositionState()
    }

    private fun initComponents() {
        postsRecyclerView = findViewById(R.id.posts_recyclerView)
        linearLayoutManager = LinearLayoutManager(this)
        progressBar = findViewById(R.id.progress_bar)
        db = DatabaseSingleton.getDatabase(applicationContext)
        reflesh_button = findViewById(R.id.reflesh_button)

        sharedPreferences = getSharedPreferences("StateConfiguration", MODE_PRIVATE)

        lastSavedPosition = sharedPreferences.getInt("recyclerViewPosition", 0)
        isAtSavedPosition = sharedPreferences.getBoolean("isAtSavedPosition", true)
    }

    private fun loadPost() {
        PostRecyclerViewLoader(
            this,
            postsRecyclerView,
            linearLayoutManager,
            progressBar,
            db,
            reflesh_button
        ).loadRecyclerView()
    }

    private fun setPositionState() {
        if (isAtSavedPosition) {
            linearLayoutManager.scrollToPosition(lastSavedPosition)
            isAtSavedPosition = false
            sharedPreferences.edit().putBoolean("isAtSavedPosition", isAtSavedPosition).apply()
        }
    }

    override fun onPause() {
        super.onPause()
        lastSavedPosition = linearLayoutManager.findFirstVisibleItemPosition()
        sharedPreferences.edit().putInt("recyclerViewPosition", lastSavedPosition).apply()
        sharedPreferences.edit().putBoolean("isAtSavedPosition", true).apply()
    }

    override fun onStop() {
        super.onStop()
        lastSavedPosition = linearLayoutManager.findFirstVisibleItemPosition()
        sharedPreferences.edit().putInt("recyclerViewPosition", lastSavedPosition).apply()
        sharedPreferences.edit().putBoolean("isAtSavedPosition", true).apply()
    }

}