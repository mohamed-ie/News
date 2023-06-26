package com.example.news.ui.screens.home.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.model.Country
import com.example.news.domain.use_case.IsInternetConnectedUseCase
import com.example.news.domain.use_case.countries.GetCountriesUseCase
import com.example.news.domain.use_case.settings.GetArticlesRetrievalCountUseCase
import com.example.news.domain.use_case.settings.GetTopHeadlinesAutoRefreshIntervalUseCase
import com.example.news.domain.use_case.settings.GetTopHeadlinesCountryCodeUseCase
import com.example.news.domain.use_case.settings.IsTopHeadlinesAutoRefreshEnabledUseCase
import com.example.news.domain.use_case.settings.UpdateArticlesRetrievalCountUseCase
import com.example.news.domain.use_case.settings.UpdateTopHeadlinesAutoRefreshUseCase
import com.example.news.domain.use_case.settings.UpdateTopHeadlinesCountryCodeUseCase
import com.example.news.domain.use_case.settings.UpdateTopHeadlinesRefreshIntervalUseCase
import com.example.news.ui.screens.home.settings.view.SettingsEvent
import com.example.news.ui.screens.home.settings.view.SettingsState
import com.example.news.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    isTopHeadlinesAutoRefreshEnabledUseCase: IsTopHeadlinesAutoRefreshEnabledUseCase,
    getTopHeadlinesAutoRefreshIntervalUseCase: GetTopHeadlinesAutoRefreshIntervalUseCase,
    getTopHeadlinesCountryCodeUseCase: GetTopHeadlinesCountryCodeUseCase,
    getArticlesRetrievalCountUseCase: GetArticlesRetrievalCountUseCase,
    private val updateTopHeadlinesAutoRefreshUseCase: UpdateTopHeadlinesAutoRefreshUseCase,
    private val updateTopHeadlinesRefreshIntervalUseCase: UpdateTopHeadlinesRefreshIntervalUseCase,
    private val updateTopHeadlinesCountryCodeUseCase: UpdateTopHeadlinesCountryCodeUseCase,
    private val updateArticlesRetrievalCountUseCase: UpdateArticlesRetrievalCountUseCase,
    private val getCountriesUseCase: GetCountriesUseCase,
    private val isInternetConnectedUseCase: IsInternetConnectedUseCase
) : ViewModel() {

    var state by mutableStateOf(SettingsState())
        private set

    private var countries = mutableListOf<Country>()

    init {
        combine(
            flow = isTopHeadlinesAutoRefreshEnabledUseCase(),
            flow2 = getTopHeadlinesAutoRefreshIntervalUseCase(),
            flow3 = getTopHeadlinesCountryCodeUseCase(),
            flow4 = getArticlesRetrievalCountUseCase()
        ) { autoRefresh, refreshInterval, countryCode, retrieveCount ->
            state.copy(
                refreshInterval = refreshInterval,
                autoRefresh = autoRefresh,
                retrieveCount = retrieveCount,
                country = countryCode
            )
        }.onEach {
            state = state.copy(
                refreshInterval = it.refreshInterval,
                autoRefresh = it.autoRefresh,
                retrieveCount = it.retrieveCount,
                country = it.country
            )
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.CountryChanged -> {
                updateTopHeadlinesCountryCodeUseCase(event.code)
                state = state.copy(isCountriesDialogVisible = false)
            }

            SettingsEvent.HideCountriesDialog ->
                state = state.copy(isCountriesDialogVisible = false)

            SettingsEvent.HideRetrievalCountDialog ->
                state = state.copy(isRetrieveCountDialogVisible = false)

            SettingsEvent.HideRefreshIntervalDialog ->
                state = state.copy(isRefreshIntervalDialogVisible = false)

            is SettingsEvent.RetrievalCountChanged -> {
                updateArticlesRetrievalCountUseCase(event.count)
                state = state.copy(isRetrieveCountDialogVisible = false)
            }

            SettingsEvent.ShowCountriesDialog ->
                showCountriesDialog()


            SettingsEvent.ShowRetrievalCountDialog ->
                state = state.copy(isRetrieveCountDialogVisible = true)

            SettingsEvent.ShowRefreshIntervalDialog ->
                state = state.copy(isRefreshIntervalDialogVisible = true)

            SettingsEvent.ToggleAutoRefresh ->
                updateTopHeadlinesAutoRefreshUseCase(state.autoRefresh.not())

            is SettingsEvent.RefreshIntervalChanged -> {
                updateTopHeadlinesRefreshIntervalUseCase(event.res)
                state = state.copy(isRefreshIntervalDialogVisible = false)
            }

            is SettingsEvent.SearchQueryChanged -> {
                state = state.copy(query = event.query)
                filterCountries(event.query)
            }
        }
    }

    private fun showCountriesDialog() {
        viewModelScope.launch {
            if (isInternetConnectedUseCase().first()) {
                state = state.copy(isCountriesDialogVisible = true)
                if (countries.isEmpty())
                    loadCountries()
            }
        }
    }

    private fun filterCountries(query: String) {
        val filtered = countries.filter {
            it.code.contains(query, true)
                    || it.name.contains(query, true)
                    || it.nativeName.contains(query, true)
        }
        state = state.copy(countries = filtered)
    }

    private fun loadCountries() {
        getCountriesUseCase().onEach { resource ->
            when (resource) {
                is Resource.Error -> {}
                is Resource.Success -> {
                    state = state.copy(countries = resource.data)
                    countries = resource.data.toMutableList()
                }
            }
        }.launchIn(viewModelScope)
    }

}