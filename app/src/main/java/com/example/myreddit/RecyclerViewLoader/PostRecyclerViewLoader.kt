package com.example.myreddit.RecyclerViewLoader

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myreddit.API.APIManager.APIDataLoader
import com.example.myreddit.Adapters.AdapterRecyclerView
import com.example.myreddit.Database.Database
import com.example.myreddit.Listeners.ScroollingRecyclerViewListener
import com.example.myreddit.Listeners.RefleshButtonListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostRecyclerViewLoader(
    private val context: Context,
    private val recyclerView: RecyclerView,
    private val linearLayoutManager: LinearLayoutManager,
    private val progressBar: ProgressBar,
    private val database: Database,
    private val reflesh_button: ImageButton
) {

    private lateinit var adapter: AdapterRecyclerView
    private val apiLoader = APIDataLoader(database)
    private val recyclerViewPagination = RecyclerViewPagination(apiLoader, database)

    fun loadRecyclerView() {
        CoroutineScope(Dispatchers.Main).launch {
            progressBar.visibility = View.VISIBLE
            val cachedPost = withContext(Dispatchers.IO) {
                database.databaseService().getAllPosts()
            }
            if (cachedPost.isNotEmpty()) {
                adapter = AdapterRecyclerView(context, cachedPost.toMutableList())
                Log.d("cashedPost", cachedPost.toString())
                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = adapter
                progressBar.visibility = View.GONE
            } else {
                val postLoader = apiLoader.fetchTopPosts()

                adapter = AdapterRecyclerView(context, postLoader.toMutableList())
                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = adapter
                progressBar.visibility = View.GONE
            }

            val scroollingRecyclerViewListener =
                ScroollingRecyclerViewListener(linearLayoutManager, { adapter.itemCount }) {
                    CoroutineScope(Dispatchers.Main).launch {
                        progressBar.visibility = View.VISIBLE
                        recyclerViewPagination.getMorePost(adapter)
                        progressBar.visibility = View.GONE
                    }

                }

            recyclerView.addOnScrollListener(scroollingRecyclerViewListener)
            reflesh_button.setOnClickListener(RefleshButtonListener(adapter, database))

            progressBar.visibility = View.GONE

        }
    }
}