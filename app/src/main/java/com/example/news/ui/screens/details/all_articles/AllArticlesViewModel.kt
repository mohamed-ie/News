package com.example.news.ui.screens.details.all_articles

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.news.base.BaseViewModel
import com.example.news.domain.use_case.GetTopHeadlinesUseCase
import com.example.news.ui.common.ArticlesData
import com.example.news.ui.navigation.screen.DetailsScreen
import com.example.news.ui.screens.details.all_articles.view.AllArticlesEvent
import com.example.news.ui.screens.details.all_articles.view.AllArticlesState
import com.example.news.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllArticlesViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _state = MutableStateFlow(AllArticlesState())
    val state = _state.asStateFlow()

    private var category: String = savedStateHandle[DetailsScreen.AllArticles.args[0].name]!!

    init {
        loadArticles()
    }

    private fun loadArticles(page: Int = 1) =
        viewModelScope.launch {
            getTopHeadlinesUseCase(
                category = category,
                query = _state.value.searchQuery,
                page = page
            ).onEach { resource ->
                when (resource) {
                    is Resource.Error -> toErrorScreenState()

                    is Resource.Success -> {
                        _state.update { it.copy(articlesData = it.articlesData + resource.data) }
                        toStableScreenState()
                        updateLoadMoreVisibility()
                    }
                }
            }.launchIn(this)
        }


    fun onEvent(event: AllArticlesEvent) {
        when (event) {
            AllArticlesEvent.Search -> {
                _state.update { it.copy(articlesData = ArticlesData(), isLoadMoreVisible = false) }
                loadArticles()
            }

            AllArticlesEvent.LoadMore ->       
                loadArticles(page = state.value.articlesData.page + 1)


            is AllArticlesEvent.SearchQueryChanged ->
                _state.update { it.copy(searchQuery = event.query) }
        }
    }


    private fun updateLoadMoreVisibility() {
        _state.update {
            val visible = it.articlesData.total > it.articlesData.pageSize * it.articlesData.page
            it.copy(isLoadMoreVisible = visible)
        }
    }
}