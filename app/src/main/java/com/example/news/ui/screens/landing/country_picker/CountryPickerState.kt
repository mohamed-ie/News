package com.example.news.ui.screens.landing.country_picker

import com.example.news.domain.model.Country
import com.example.news.utils.ScreenState

data class CountryPickerState(
    val selectedCode: String = "",
    val isDoneButtonVisible: Boolean = false,
    val isClearButtonVisible: Boolean =false,

    val query: String = "",
    val countries: List<Country> = emptyList()
)
