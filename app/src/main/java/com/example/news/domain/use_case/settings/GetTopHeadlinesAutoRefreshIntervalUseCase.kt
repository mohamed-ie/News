package com.example.news.domain.use_case.settings

import com.example.news.R
import com.example.news.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopHeadlinesAutoRefreshIntervalUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<Int> = repository.refreshMills
}