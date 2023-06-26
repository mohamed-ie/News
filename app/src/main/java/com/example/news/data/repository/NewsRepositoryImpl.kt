package com.example.news.data.repository

import com.example.news.data.local.db.entity.Article
import com.example.news.di.DefaultDispatcher
import com.example.news.domain.data.NewsLocalDataSource
import com.example.news.domain.data.NewsRemoteDataSource
import com.example.news.domain.data.RemoteErrorHandler
import com.example.news.domain.repository.NewsRepository
import com.example.news.ui.common.ArticlesData
import com.example.news.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource,
    private val remoteErrorHandler: RemoteErrorHandler,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : NewsRepository {

    override val bookmarkedArticles: Flow<List<Article>> =
        localDataSource.articles

    override suspend fun bookmark(article: Article) =
        withContext(defaultDispatcher) {
            localDataSource.addArticle(article) != 0L
        }


    override suspend fun deleteBookmarkByUrl(url: String) =
        withContext(defaultDispatcher) {
            localDataSource.deleteArticle(url) != 0
        }

    override suspend fun isBookmarked(url: String): Boolean  =
        withContext(defaultDispatcher) {
            localDataSource.isBookmarked(url)  == 1
        }

    override fun getTopHeadlines(
        country: String,
        category: String,
        query: String,
        page: Int,
        pageSize: Byte,
        autoRefresh: Boolean,
        refreshMills: Long
    ): Flow<Resource<ArticlesData>> =
        remoteDataSource.getTopHeadlines(
            country = country,
            category = category,
            query = query,
            pageSize = pageSize,
            page = page,
            autoRefresh = autoRefresh,
            refreshMillSec = refreshMills
        )
            .map { dto ->
                val urls = localDataSource.articlesUrls.first()
                val articles = dto.articles
                    .map { it.toArticle(isBookmarked = urls.contains(it.url)) }
                val articlesData = ArticlesData(
                    total = dto.totalResults,
                    page = page,
                    pageSize = pageSize,
                    articles = articles
                )
                Resource.Success(articlesData) as Resource<ArticlesData>
            }
            .catch { remoteErrorHandler.handle(it, this) }
            .flowOn(defaultDispatcher)

    override fun searchArticles(
        query: String,
        searchIn: String,
        from: String,
        to: String,
        sortBy: String,
        language: String,
        pageSize: Byte,
        page: Int,
    ): Flow<Resource<ArticlesData>> =
        remoteDataSource.searchArticles(
            query = query,
            searchIn = searchIn,
            from = from,
            to = to,
            sortBy = sortBy,
            language = language,
            pageSize = pageSize,
            page = page
        )
            .map { dto ->
                val urls = localDataSource.articlesUrls.first()
                val articlesData = ArticlesData(
                    total = dto.totalResults,
                    page = page,
                    pageSize = pageSize,
                    articles = dto.articles.map { it.toArticle( urls.contains(it.url)) }
                )
                Resource.Success(articlesData) as Resource<ArticlesData>
            }
            .catch { remoteErrorHandler.handle(it, this) }
            .flowOn(defaultDispatcher)


}