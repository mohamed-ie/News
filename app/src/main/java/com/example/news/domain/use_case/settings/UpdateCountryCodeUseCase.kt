package com.example.news.domain.use_case.settings

import com.example.news.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateCountryCodeUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(code: String) {
        repository.updateCountry(code)
    }
}