package com.example.news.domain.use_case

import android.text.format.DateFormat
import com.example.news.R
import com.example.news.common.Constants
import com.example.news.domain.repository.NewsRepository
import com.example.news.ui.common.ArticlesData
import com.example.news.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(
        query: String,
        searchInOptions: List<Int>,
        fromMills: Long,
        toMills: Long,
        sortBy: Int,
        language: String,
        pageSize: Byte = 100,
        page: Int = 1,
    ): Flow<Resource<ArticlesData>> {
        val searchIn = searchInOptions.joinToString(separator = ",") {
            when (it) {
                R.string.title -> "title"
                R.string.description -> "description"
                else -> "content"
            }
        }
        val from = DateFormat.format(Constants.NEWS_DATE_PATTERN, fromMills)?.toString() ?: ""
        val to = DateFormat.format(Constants.NEWS_DATE_PATTERN, toMills)?.toString() ?: ""
        val sortBy = when (sortBy) {
            R.string.relevancy -> "relevancy"
            R.string.popularity -> "popularity"
            else -> "publishedAt"
        }
        val language = if (language == "all") "" else language
        return repository.searchArticles(
            query,
            searchIn,
            from,
            to,
            sortBy,
            language,
            pageSize,
            page
        )
    }
}