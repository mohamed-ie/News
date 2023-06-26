package com.example.news.domain.use_case.article

import com.example.news.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteBookmarkedArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(url:String) =
        repository.deleteBookmarkByUrl(url)
}