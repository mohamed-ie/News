package com.example.news.ui.screens.home.settings.view

sealed interface SettingsEvent {
    object ShowCountriesDialog : SettingsEvent
    object HideCountriesDialog : SettingsEvent
    object ShowRefreshIntervalDialog : SettingsEvent
    object HideRefreshIntervalDialog : SettingsEvent
    object ShowRetrievalCountDialog : SettingsEvent
    object HideRetrievalCountDialog : SettingsEvent
    object ToggleAutoRefresh : SettingsEvent

    class CountryChanged(val code: String) : SettingsEvent
    class RefreshIntervalChanged(val res: Int) : SettingsEvent
    class RetrievalCountChanged(val count: Int) : SettingsEvent
    class SearchQueryChanged(val query: String) : SettingsEvent
}
