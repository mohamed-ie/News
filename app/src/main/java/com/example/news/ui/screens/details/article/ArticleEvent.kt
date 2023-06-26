package com.example.news.ui.screens.details.article

sealed interface ArticleEvent {
    object ToggleMoreMenuExpandState : ArticleEvent
    object Bookmark : ArticleEvent
}
