package com.example.news.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.news.data.local.db.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

    @Insert
    suspend fun insert(article: Article): Long

    @Query("DELETE FROM article WHERE url=:url")
    suspend fun delete(url: String): Int

    @Query("SELECT 1 FROM article WHERE url=:url")
    fun isBookmarked(url: String): Int

    @get:Query("SELECT url FROM article")
    val urls: Flow<List<String>>

    @get:Query("SELECT * FROM article")
    val articles: Flow<List<Article>>
}