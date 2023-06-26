package com.example.news.data.local

import com.example.news.data.local.db.ArticlesDao
import com.example.news.data.local.db.entity.Article
import com.example.news.di.IODispatcher
import com.example.news.domain.data.NewsLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(
    private val dao: ArticlesDao,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher
) : NewsLocalDataSource {

    override val articles: Flow<List<Article>> =
        dao.articles.flowOn(ioDispatcher)

    override val articlesUrls: Flow<List<String>> =
        dao.urls.flowOn(ioDispatcher)

    override suspend fun addArticle(article: Article) =
        withContext(ioDispatcher) {
            dao.insert(article)
        }

    override suspend fun deleteArticle(url: String) =
        withContext(ioDispatcher) {
            dao.delete(url)
        }

    override suspend fun isBookmarked(url: String)=
        withContext(ioDispatcher) {
        dao.isBookmarked(url)
    }
}