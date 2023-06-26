package com.example.news.domain.use_case

import com.example.news.R
import com.example.news.common.ArticlesRetrievalCount
import com.example.news.common.RefreshInterval
import com.example.news.domain.repository.NewsRepository
import com.example.news.domain.repository.SettingsRepository
import com.example.news.ui.common.ArticlesData
import com.example.news.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val settingsRepository: SettingsRepository
) {

    suspend operator fun invoke(
        country: String = "",
        category: String = "",
        query: String = "",
        page: Int = 1,
        autoRefresh: Boolean? = null
    ): Flow<Resource<ArticlesData>> {
        return newsRepository.getTopHeadlines(
            country = country.ifBlank { settingsRepository.countryCode.first() },
            category = category,
            query = query,
            page = page,
            pageSize = ArticlesRetrievalCount.from(settingsRepository.pageSize.first()),
            autoRefresh = autoRefresh ?: settingsRepository.autoRefresh.first(),
            refreshMills = RefreshInterval.from(settingsRepository.refreshMills.first())
        )
    }

}