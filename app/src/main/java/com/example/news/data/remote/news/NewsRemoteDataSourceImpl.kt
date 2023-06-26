package com.example.news.data.remote.news

import com.example.news.data.remote.news.dto.news.NewsDTO
import com.example.news.data.remote.news.dto.source.SourcesDTO
import com.example.news.di.IODispatcher
import com.example.news.domain.data.NewsRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val webservice: NewsWebservice,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher
) : NewsRemoteDataSource {

    override fun searchArticles(
        query: String,
        searchIn: String,
        from: String,
        to: String,
        sortBy: String,
        language: String,
        pageSize: Byte,
        page: Int
    ): Flow<NewsDTO> = flow {
        val response = webservice.getEverything(
            sources = "",
            query = query,
            searchIn = searchIn,
            from = from,
            to = to,
            sortBy = sortBy,
            language = language,
            pageSize = pageSize,
            page = page
        )
        emit(response)
    }.flowOn(ioDispatcher)

    override fun getRecommendedArticles(
        sources: String,
        pageSize: Byte,
        page: Int
    ): Flow<NewsDTO> = flow {
        //maybe do some logic later
        val response = webservice.getEverything(
            sources = sources,
            query = "",
            searchIn = "",
            from = "",
            to = "",
            sortBy = "",
            language = "",
            pageSize = pageSize,
            page = page
        )
        emit(response)
    }.flowOn(ioDispatcher)


    override fun getSources(): Flow<SourcesDTO> = flow {
        val response = webservice.getSources()
        emit(response)
    }.flowOn(ioDispatcher)

    override fun getTopHeadlines(
        country: String,
        category: String,
        query: String,
        pageSize: Byte,
        page: Int,
        autoRefresh: Boolean,
        refreshMillSec: Long
    ): Flow<NewsDTO> = flow {
        do {
            val response =
                webservice.getTopHeadlines(
                    country = country,
                    category = category,
                    query = query,
                    pageSize = pageSize,
                    page = page
                )
            emit(response)
            delay(refreshMillSec)
        } while (autoRefresh)
    }.flowOn(ioDispatcher)

}