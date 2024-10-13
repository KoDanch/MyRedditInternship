package com.example.myreddit.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myreddit.DataModel.DataModel
import com.example.myreddit.Adapters.ViewHolder.ViewHolderPost
import com.example.myreddit.Glide.GlideManager
import com.example.myreddit.R

class AdapterRecyclerView(
    private val dataSet: MutableList<DataModel>
) : RecyclerView.Adapter<ViewHolderPost>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPost {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview, parent, false)

        return ViewHolderPost(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPost, position: Int) {
        val itemViewHolder = dataSet[position]

        holderCoordinator(holder, itemViewHolder)
    }

    private fun holderCoordinator(holder: ViewHolderPost, dataModel: DataModel) {
        holder.text_post_recyclerView.text = dataModel.text
        holder.nickname_user.text = dataModel.nickname
        holder.count_comments.text = dataModel.count_comments.toString()
        getMedia(holder, dataModel)
    }

    private fun getMedia(holder: ViewHolderPost, dataModel: DataModel) {
        val UrlToString: String = dataModel.imageUrl.toString()
        val Glide = GlideManager()

        if (!UrlToString.contains("null")) {
            holder.post_thumbnail.visibility = View.VISIBLE

            Glide.loadAvatar(holder.avatar_user, dataModel.avatarUrl)
            Glide.loadMedia(holder.post_thumbnail, dataModel.imageUrl)

        } else {
            holder.post_thumbnail.visibility = View.GONE
        }
    }

    override fun getItemCount() = dataSet.size

}