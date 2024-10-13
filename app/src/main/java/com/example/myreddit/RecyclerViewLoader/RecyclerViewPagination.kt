package com.example.myreddit.RecyclerViewLoader

import com.example.myreddit.API.APIManager.APIDataLoader
import com.example.myreddit.Adapters.AdapterRecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecyclerViewPagination(private val apiLoader: APIDataLoader) {

    suspend fun getMorePost(adapter: AdapterRecyclerView) {

        withContext(Dispatchers.Main) {
            val newPosts = apiLoader.fetchTopPosts()
            if (newPosts.isNotEmpty()) {
                val currentItemCount = adapter.itemCount
                adapter.addPosts(newPosts)

                adapter.notifyItemRangeInserted(currentItemCount, newPosts.size)
            }
        }
    }
}