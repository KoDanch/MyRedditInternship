package com.example.myreddit.RecyclerViewLoader

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myreddit.API.APIManager.APIDataLoader
import com.example.myreddit.Adapters.AdapterRecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostRecyclerViewLoader(
    private val context: Context,
    private val recyclerView: RecyclerView,
    private val linearLayoutManager: LinearLayoutManager,
    private val progressBar: ProgressBar
) {

    private lateinit var adapter: AdapterRecyclerView

    fun loadRecyclerView() {
        CoroutineScope(Dispatchers.Main).launch {
            progressBar.visibility = View.VISIBLE

            val apiLoader = APIDataLoader()
            val postLoader = apiLoader.fetchTopPosts()

            adapter = AdapterRecyclerView(context, postLoader.toMutableList())
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = adapter

            progressBar.visibility = View.GONE


        }
    }

}