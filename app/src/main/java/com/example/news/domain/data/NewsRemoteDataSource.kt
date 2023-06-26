package com.example.news.domain.data

import com.example.news.data.remote.news.dto.news.NewsDTO
import com.example.news.data.remote.news.dto.source.SourcesDTO
import kotlinx.coroutines.flow.Flow

interface NewsRemoteDataSource {
    fun getRecommendedArticles(
        sources: String,
        pageSize: Byte,
        page: Int,
    ): Flow<NewsDTO>

    fun getSources(): Flow<SourcesDTO>

    fun getTopHeadlines(
        country: String,
        category: String,
        query: String,
        pageSize: Byte,
        page: Int,
        autoRefresh: Boolean,
        refreshMillSec: Long
    ): Flow<NewsDTO>

    fun searchArticles(
        query: String,
        searchIn: String,
        from: String,
        to: String,
        sortBy: String,
        language: String,
        pageSize: Byte,
        page: Int
    ): Flow<NewsDTO>
}