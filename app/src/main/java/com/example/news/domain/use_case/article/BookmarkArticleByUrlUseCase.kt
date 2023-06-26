package com.example.news.domain.use_case.article

import com.example.news.data.local.db.entity.Article
import com.example.news.domain.repository.NewsRepository
import javax.inject.Inject

class BookmarkArticleByUrlUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(article: Article) =
        repository.bookmark(article)
}