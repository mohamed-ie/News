package com.example.news.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.data.local.db.entity.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "APP_DB"
    }
    abstract val dao: ArticlesDao
}