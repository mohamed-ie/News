package com.example.news.domain.use_case.article

import com.example.news.domain.repository.NewsRepository
import javax.inject.Inject

class IsArticleBookmarkedUseCase  @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(url:String) =
        repository.isBookmarked(url)

}