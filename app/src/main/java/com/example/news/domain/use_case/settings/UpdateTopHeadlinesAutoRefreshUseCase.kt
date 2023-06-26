package com.example.news.domain.use_case.settings

import com.example.news.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTopHeadlinesAutoRefreshUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(value: Boolean) = repository.updateAutoRefresh(value)
}