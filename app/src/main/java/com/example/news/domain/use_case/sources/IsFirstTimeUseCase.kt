package com.example.news.domain.use_case.sources

import com.example.news.domain.repository.SettingsRepository
import javax.inject.Inject

class IsFirstTimeUseCase @Inject constructor(
    private val repository: SettingsRepository
) {

    operator fun invoke() = repository.isFirstTime
}