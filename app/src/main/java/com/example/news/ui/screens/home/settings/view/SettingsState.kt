package com.example.news.ui.screens.home.settings.view

import com.example.news.R
import com.example.news.domain.model.Country

data class SettingsState(
    val countries: List<Country> = emptyList(),
    val isCountriesDialogVisible: Boolean = false,
    val isRetrieveCountDialogVisible: Boolean = false,
    val isRefreshIntervalDialogVisible: Boolean = false,

    val autoRefresh: Boolean = false,
    val refreshInterval: Int = R.string.every_twenty_minute,
    val retrieveCount: Int = R.string.twenty,
    val country: String = "",

    val query: String = ""

)