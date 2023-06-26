package com.example.news.ui.screens.home.settings.view.component

import com.example.news.domain.model.Country

data class CountriesDialogState(
    val searchQuery: String = "",
    val countries: List<Country> = emptyList()
)
