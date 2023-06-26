package com.example.news.ui.screens.search

import android.text.format.DateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.use_case.SearchArticlesUseCase
import com.example.news.ui.screens.search.view.SearchEvent
import com.example.news.ui.screens.search.view.component.ManageSearchEvent
import com.example.news.ui.screens.search.view.component.ManageSearchState
import com.example.news.ui.screens.search.view.SearchState
import com.example.news.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchArticlesUseCase: SearchArticlesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val _manageSearchDialogState = MutableStateFlow(ManageSearchState())
    val manageSearchDialogState = _manageSearchDialogState.asStateFlow()

    private var manageSearchOldState = _manageSearchDialogState.value

    private var searchJob: Job? = null

    fun onManageSearchDialogEvent(event: ManageSearchEvent) {
        when (event) {
            is ManageSearchEvent.SearchInOptionChanged -> updateSearchInOption(event.option)
            is ManageSearchEvent.LanguageChanged -> changeLanguage(event.value)
            is ManageSearchEvent.SortOptionChanged -> changeSortOption(event.value)
        }
    }

    private fun updateSearchInOption(option: Int) {
        _manageSearchDialogState.update {
            it.copy(selectedSearchInOptions = it.selectedSearchInOptions.toMutableList().apply {
                if (indexOf(option) == -1)
                    add(option)
                else
                    remove(option)
            })
        }
    }

    private fun changeLanguage(value: String) {
        _manageSearchDialogState.update {
            it.copy(selectedLanguage = value)
        }
    }

    private fun changeSortOption(value: Int) {
        _manageSearchDialogState.update {
            it.copy(selectedSortOption = value)
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchQueryChanged -> searchQueryChanged(event.query)
            SearchEvent.ShowManageSearchDialog -> showManageSearchDialog()
            SearchEvent.DismissManageSearchDialog -> changeManageSearchDialogVisibility(false)
            SearchEvent.ShowDateRangePickerDialog -> changeDateRangePickerVisibility(true)
            SearchEvent.HideDateRangePickerDialog -> changeDateRangePickerVisibility(false)
            is SearchEvent.DateRangeChanged -> changeDateRange(event.startMills, event.endMills)
            SearchEvent.ApplySearchOption -> applySearchOptions()
            SearchEvent.Search -> search()
            SearchEvent.LoadMore -> loadMore()
        }
    }

    private fun loadMore() {
        with(manageSearchOldState) {
            searchJob = searchArticlesUseCase(
                query = state.value.searchQuery,
                searchInOptions = selectedSearchInOptions,
                fromMills = startMills,
                page = state.value.articlesData.page + 1,
                toMills = endMills,
                sortBy = selectedSortOption,
                language = selectedLanguage,
            ).onEach { resource ->
                when (resource) {
                    is Resource.Error -> {}
                    is Resource.Success -> {
                        _state.update {
                            it.copy(articlesData = it.articlesData + resource.data )
                        }
                        updateLoadMoreVisibility()
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun updateLoadMoreVisibility() {
        _state.update {
            val visible = it.articlesData.total > it.articlesData.pageSize * it.articlesData.page
            it.copy(isLoadMoreVisible = visible)
        }
    }

    private fun searchQueryChanged(query: String) {
        _state.update {
            it.copy(searchQuery = query, isSearchVisible = query.isNotBlank())
        }
    }

    private fun changeManageSearchDialogVisibility(visible: Boolean) {
        _state.update {
            it.copy(isManageSearchDialogVisible = visible)
        }
    }

    private fun showManageSearchDialog() {
        _manageSearchDialogState.update { manageSearchOldState }
        changeManageSearchDialogVisibility(true)
    }

    private fun changeDateRangePickerVisibility(visible: Boolean) {
        _state.update {
            it.copy(isDateRangePickerDialogVisible = visible)
        }
    }

    private fun changeDateRange(startMills: Long, endMills: Long) {
        _manageSearchDialogState.update {
            it.copy(
                startMills = startMills,
                endMills = endMills,
                fromDate = DateFormat.format(ManageSearchState.DATE_PATTERN, startMills).toString(),
                toDate = DateFormat.format(ManageSearchState.DATE_PATTERN, endMills).toString()
            )
        }
        _state.update {
            it.copy(isDateRangePickerDialogVisible = false)
        }
    }


    private fun applySearchOptions() {
        changeManageSearchDialogVisibility(false)
        manageSearchOldState = _manageSearchDialogState.value
    }


    private fun search() {
        with(manageSearchOldState) {
            searchJob = searchArticlesUseCase(
                query = state.value.searchQuery,
                searchInOptions = selectedSearchInOptions,
                fromMills = startMills,
                toMills = endMills,
                sortBy = selectedSortOption,
                language = selectedLanguage,
            ).onEach { resource ->
                when (resource) {
                    is Resource.Error -> {}
                    is Resource.Success -> {
                        _state.update {
                            it.copy(articlesData = resource.data)
                        }
                        updateLoadMoreVisibility()
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}
