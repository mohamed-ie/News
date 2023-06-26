package com.example.news.ui.screens.search.view

import com.example.news.ui.common.ArticlesData

data class SearchState(
    val articlesData: ArticlesData = ArticlesData(),
    val isManageSearchDialogVisible: Boolean = false,
    val isDateRangePickerDialogVisible: Boolean = false,
    val searchQuery: String = "",
    val isSearchVisible: Boolean = searchQuery.isNotBlank(),
    val isLoadMoreVisible: Boolean = false
)
