package com.example.myreddit.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myreddit.DataModel.DataModel

@Database(entities = [DataModel::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun databaseService(): DatabaseService
}