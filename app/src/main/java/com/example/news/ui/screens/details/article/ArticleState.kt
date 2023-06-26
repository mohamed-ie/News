package com.example.news.ui.screens.details.article

import com.example.news.data.local.db.entity.Article
import com.example.news.utils.ScreenState

data class ArticleState(
    val article: Article = Article(),
    val isMoreMenuExpanded: Boolean = false
)