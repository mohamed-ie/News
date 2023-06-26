package com.example.news.domain.data

import com.example.news.data.local.db.entity.Article
import kotlinx.coroutines.flow.Flow


interface NewsLocalDataSource {
    val articles: Flow<List<Article>>
    val articlesUrls: Flow<List<String>>

    suspend fun addArticle(article: Article): Long
    suspend fun deleteArticle(url: String): Int
    suspend fun isBookmarked(url: String): Int
}