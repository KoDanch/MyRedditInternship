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
    private val apiLoader = APIDataLoader()
    private val recyclerViewPagination = RecyclerViewPagination(apiLoader)

    fun loadRecyclerView() {
        CoroutineScope(Dispatchers.Main).launch {
            progressBar.visibility = View.VISIBLE
            val postLoader = apiLoader.fetchTopPosts()
            adapter = AdapterRecyclerView(context, postLoader.toMutableList())
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = adapter

            val actionRecyclerViewHelper =
                ActionRecyclerViewHelper(linearLayoutManager, { adapter.itemCount }) {
                    CoroutineScope(Dispatchers.Main).launch {
                        progressBar.visibility = View.VISIBLE
                        recyclerViewPagination.getMorePost(adapter)
                        progressBar.visibility = View.GONE
                    }

                }

            recyclerView.addOnScrollListener(actionRecyclerViewHelper)

            progressBar.visibility = View.GONE

        }
    }
}