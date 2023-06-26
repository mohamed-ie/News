package com.example.news.ui.screens.search.view.component

sealed interface ManageSearchEvent {
    class SearchInOptionChanged(val option: Int) : ManageSearchEvent
    class LanguageChanged(val value: String) : ManageSearchEvent
    class SortOptionChanged(val value: Int) : ManageSearchEvent

}
