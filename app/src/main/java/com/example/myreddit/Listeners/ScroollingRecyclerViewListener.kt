package com.example.myreddit.Listeners

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScroollingRecyclerViewListener(
    private val layoutManager: LinearLayoutManager,
    private val itemCount: () -> Int,
    private val loadMore: () -> Unit
) : RecyclerView.OnScrollListener() {
    private var isLoadingNewPost = false
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val lastItem = layoutManager.findLastVisibleItemPosition()
        val totalItem = itemCount()

        if (lastItem >= totalItem - 4 && !isLoadingNewPost) {
            isLoadingNewPost = true
            CoroutineScope(Dispatchers.Main).launch {
                loadMore()

                Handler(Looper.getMainLooper()).postDelayed({
                    isLoadingNewPost = false
                }, 3000)
            }
        }
    }
}