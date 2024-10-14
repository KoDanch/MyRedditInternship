package com.example.myreddit.Database

import android.content.Context
import androidx.room.Room

object DatabaseSingleton {
    private var INSTANCE: Database? = null

    fun getDatabase(context: Context): Database {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                Database::class.java,
                "post_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}