package com.example.news.ui.screens.home.home

import androidx.lifecycle.viewModelScope
import com.example.news.base.BaseViewModel
import com.example.news.domain.use_case.GetTopHeadlinesUseCase
import com.example.news.domain.use_case.settings.GetTopHeadlinesCountryCodeUseCase
import com.example.news.ui.common.ArticlesData
import com.example.news.ui.screens.home.home.view.HomeState
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
class HomeViewModel @Inject constructor(
    getTopHeadlinesCountryCodeUseCase: GetTopHeadlinesCountryCodeUseCase,
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
) : BaseViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    private var nextPage: Int = 1

    init {
        getTopHeadlinesCountryCodeUseCase().onEach {
            loadArticles()
            nextPage = 1
        }.launchIn(viewModelScope)
    }


    private fun loadArticles(page: Int = 1) = viewModelScope.launch {
        getTopHeadlinesUseCase(page = page).onEach { resource ->
            when (resource) {
                is Resource.Error -> toErrorScreenState()
                is Resource.Success -> {
                    updateArticles(resource.data)
                    toStableScreenState()
                }
            }
        }.launchIn(this)
    }

    private fun updateArticles(data: ArticlesData) = _state.update { oldState ->
        updateLoadMoreVisibility(data.total, data.pageSize, data.page)
        if (oldState.articles.isEmpty() || nextPage == 1)
            oldState.copy(
                topHeadlines = data.articles.subList(0, 5),
                articles = data.articles.subList(5, data.articles.size)
            )
        else
            oldState.copy(articles = oldState.articles + data.articles)
    }


    fun loadMore() {
        nextPage++
        loadArticles(nextPage)
    }

    private fun updateLoadMoreVisibility(total: Int, pageSize: Byte, page: Int) {
        _state.update {
            val visible = total > page * pageSize
            it.copy(isLoadMoreVisible = visible)
        }
    }


}