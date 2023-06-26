package com.example.news.domain.use_case.sources

import com.example.news.domain.repository.NewsRepository
import javax.inject.Inject

class UpdateIsFirstTimeUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(value: Boolean = false) {
    }
}