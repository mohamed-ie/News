package com.example.news.ui.screens.details.all_articles.view

import com.example.news.ui.common.ArticlesData
import com.example.news.utils.ScreenState

data class AllArticlesState(
    val articlesData: ArticlesData = ArticlesData(),
    val searchQuery: String = "",
    val isLoadMoreVisible: Boolean = false
)