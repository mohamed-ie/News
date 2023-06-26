package com.example.news.ui.common

import com.example.news.data.local.db.entity.Article
import java.lang.Integer.max

data class ArticlesData(
    val total: Int = 0,
    val page: Int = 0,
    val pageSize: Byte = 0,
    val articles: List<Article> = emptyList()
) {
    operator fun plus(articlesData: ArticlesData): ArticlesData {
        return ArticlesData(
            total = articlesData.total,
            page = max(page, articlesData.page),
            pageSize = maxOf(pageSize, articlesData.pageSize),
            articles = articles + articlesData.articles
        )
    }
}