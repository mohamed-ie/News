package com.example.news.ui.screens.landing.country_picker

sealed interface CountryPickerEvent {
    object SaveCountry : CountryPickerEvent

    class SearchQueryChanged(val query: String) : CountryPickerEvent
    class CountrySelected(val code: String) : CountryPickerEvent

}
