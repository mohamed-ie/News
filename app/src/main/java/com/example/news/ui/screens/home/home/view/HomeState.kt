package com.example.news.ui.screens.home.home.view

import com.example.news.data.local.db.entity.Article
import com.example.news.utils.ScreenState

data class HomeState(
    val topHeadlines: List<Article> = emptyList(),
    val articles: List<Article> = emptyList(),
    val isLoadMoreVisible: Boolean = false
)