package com.example.news.ui.screens.home.bookmarks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.news.base.BaseViewModel
import com.example.news.data.local.db.entity.Article
import com.example.news.domain.use_case.article.DeleteBookmarkedArticleUseCase
import com.example.news.domain.use_case.GetBookmarkedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    getBookmarkedArticlesUseCase: GetBookmarkedArticlesUseCase,
) : BaseViewModel() {
    var articles by mutableStateOf<List<Article>>(emptyList())
        private set

    init {
        getBookmarkedArticlesUseCase().onEach { articles ->
            this.articles = articles
            toStableScreenState()
        }.launchIn(viewModelScope)
    }
}