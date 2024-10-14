package com.example.myreddit.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myreddit.DataModel.DataModel

@Dao
interface DatabaseService {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<DataModel>)

    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<DataModel>

    @Query("DELETE FROM posts")
    suspend fun clearPosts()

    @Query("DELETE FROM posts WHERE id IN (:postId)")
    fun deletePostById(postId: List<String>)


}