package com.example.myreddit.Listeners

import android.util.Log
import android.view.View
import android.widget.ImageButton
import com.example.myreddit.Adapters.AdapterRecyclerView
import com.example.myreddit.Database.Database
import kotlinx.coroutines.withContext

class RefleshButtonListener(private val adapter: AdapterRecyclerView, private val db: Database) :
    View.OnClickListener {
    override fun onClick(p0: View?) {
        adapter.clearPosts(db)
    }

}