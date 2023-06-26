package com.example.news.ui.screens.details.article

import androidx.lifecycle.viewModelScope
import com.example.news.base.BaseViewModel
import com.example.news.data.local.db.entity.Article
import com.example.news.domain.use_case.article.BookmarkArticleByUrlUseCase
import com.example.news.domain.use_case.article.DeleteBookmarkedArticleUseCase
import com.example.news.domain.use_case.article.IsArticleBookmarkedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val isArticleBookmarkedUseCase: IsArticleBookmarkedUseCase,
    private val bookmarkArticleByUrlUseCase: BookmarkArticleByUrlUseCase,
    private val deleteBookmarkedArticleUseCase: DeleteBookmarkedArticleUseCase,
) : BaseViewModel() {
    private val _state = MutableStateFlow(ArticleState())
    val state = _state.asStateFlow()

    fun setArticle(article: StateFlow<Article?>) {
        article.onEach { article ->
            if (article != null) {
                _state.update { it.copy(article = updateArticleState(article)) }
                toStableScreenState()
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun updateArticleState(article: Article) =
        article.copy(isBookmarked =  isArticleBookmarkedUseCase(article.url))

    fun onEvent(event: ArticleEvent) {
        when (event) {
            ArticleEvent.Bookmark ->
                if (state.value.article.isBookmarked)
                    deleteBookmark()
                else
                    bookmark()

            ArticleEvent.ToggleMoreMenuExpandState ->
                _state.update { it.copy(isMoreMenuExpanded = it.isMoreMenuExpanded.not()) }
        }
    }

    private fun bookmark() = viewModelScope.launch {
        val isBookmarked = bookmarkArticleByUrlUseCase(state.value.article)
        val article = _state.value.article.copy(isBookmarked = isBookmarked)
        _state.update { it.copy(article = article) }
    }

    private fun deleteBookmark() = viewModelScope.launch {
        val isBookmarked = !deleteBookmarkedArticleUseCase(state.value.article.url)
        val article = _state.value.article.copy(isBookmarked = isBookmarked)
        _state.update { it.copy(article = article) }
    }
}