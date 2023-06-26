package com.example.news.ui.screens.home.home.view

import com.example.news.data.local.db.entity.Article

sealed interface HomeEvent{
    class SeeLater(val article: Article) : HomeEvent

}
