package com.example.news.domain.use_case.settings

import com.example.news.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateArticlesRetrievalCountUseCase  @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(count: Int) = repository.updatePageSize(count)
}