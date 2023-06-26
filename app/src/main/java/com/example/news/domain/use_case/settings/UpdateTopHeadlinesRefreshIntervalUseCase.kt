package com.example.news.domain.use_case.settings

import androidx.annotation.StringRes
import com.example.news.R
import com.example.news.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateTopHeadlinesRefreshIntervalUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(res: Int) {
        repository.updateRefreshMills(res)
    }
}