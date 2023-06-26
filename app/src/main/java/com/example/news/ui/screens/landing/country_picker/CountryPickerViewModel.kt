package com.example.news.ui.screens.landing.country_picker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.base.BaseViewModel
import com.example.news.domain.model.Country
import com.example.news.domain.use_case.countries.GetCountriesUseCase
import com.example.news.domain.use_case.settings.UpdateCountryCodeUseCase
import com.example.news.utils.Resource
import com.example.news.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CountryPickerViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val updateCountryCodeUseCase: UpdateCountryCodeUseCase
) :BaseViewModel() {
    private val _state = MutableStateFlow(CountryPickerState())
    val state = _state.asStateFlow()

    private var countries = emptyList<Country>()

    init {
        getCountriesUseCase().onEach { resource ->
            when (resource) {
                is Resource.Error -> toErrorScreenState()
                is Resource.Success -> {
                    _state.update { it.copy( countries = resource.data) }
                    countries = resource.data
                    toStableScreenState()
                }
            }
        }.launchIn(viewModelScope)

    }


    fun onEvent(event: CountryPickerEvent) {
        when (event) {
            is CountryPickerEvent.CountrySelected ->
                _state.update { it.copy(selectedCode = event.code, isDoneButtonVisible = true) }

            CountryPickerEvent.SaveCountry ->
                updateCountryCodeUseCase(state.value.selectedCode)


            is CountryPickerEvent.SearchQueryChanged -> {
                _state.update {
                    it.copy(
                        query = event.query,
                        isClearButtonVisible = event.query.isNotEmpty()
                    )
                }
                filterCountries(event.query)
            }

        }
    }

    private fun filterCountries(query: String) {
        val filteredCountries = countries.filter {
            it.code.contains(query, true)
                    || it.name.contains(query, true)
                    || it.nativeName.contains(query, true)
        }.sortedBy { it.name }

        _state.update { it.copy(countries = filteredCountries) }
    }
}
