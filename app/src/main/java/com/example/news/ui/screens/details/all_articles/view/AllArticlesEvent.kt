package com.example.news.ui.screens.details.all_articles.view

sealed interface AllArticlesEvent{
    object Search : AllArticlesEvent
    object LoadMore : AllArticlesEvent

    class SearchQueryChanged(val query: String) : AllArticlesEvent

}
