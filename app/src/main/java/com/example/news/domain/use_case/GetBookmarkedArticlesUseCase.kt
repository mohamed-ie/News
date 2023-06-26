package com.example.news.domain.use_case

import com.example.news.domain.repository.NewsRepository
import javax.inject.Inject

class GetBookmarkedArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke() = repository.bookmarkedArticles
}