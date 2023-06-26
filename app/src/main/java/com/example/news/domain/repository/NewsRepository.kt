package com.example.news.domain.repository

import com.example.news.data.local.db.entity.Article
import com.example.news.ui.common.ArticlesData
import com.example.news.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    val bookmarkedArticles: Flow<List<Article>>

    suspend fun bookmark(article: Article): Boolean

    suspend fun deleteBookmarkByUrl(url: String): Boolean

    suspend fun isBookmarked(url: String):Boolean

    fun searchArticles(
        query: String,
        searchIn: String,
        from: String,
        to: String,
        sortBy: String,
        language: String,
        pageSize: Byte,
        page: Int
    ): Flow<Resource<ArticlesData>>

    fun getTopHeadlines(
        country: String,
        category: String,
        query: String,
        page: Int,
        pageSize: Byte,
        autoRefresh: Boolean,
        refreshMills: Long
    ): Flow<Resource<ArticlesData>>

}