package com.example.myreddit.Activity

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myreddit.R
import com.example.myreddit.RecyclerViewLoader.PostRecyclerViewLoader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_mainactivity)
        val posts_recyclerView: RecyclerView = findViewById(R.id.posts_recyclerView)
        val linearLayoutManager = LinearLayoutManager(this)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)

        PostRecyclerViewLoader(posts_recyclerView, linearLayoutManager, progressBar).loadRecyclerView()
    }
}