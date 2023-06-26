package com.example.news.domain.use_case.sources

import com.example.news.domain.repository.NewsRepository
import com.example.news.ui.common.Source
import javax.inject.Inject

class SaveRecommendedSourceUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(sources: Map<String, List<Source>>) {

    }
}