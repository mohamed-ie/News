package com.example.news.ui.screens.search.view

sealed interface SearchEvent {
    class DateRangeChanged(val startMills: Long, val endMills: Long) : SearchEvent
    class SearchQueryChanged(val query: String) : SearchEvent

    object ShowManageSearchDialog : SearchEvent
    object DismissManageSearchDialog : SearchEvent
    object ShowDateRangePickerDialog : SearchEvent
    object HideDateRangePickerDialog : SearchEvent
    object ApplySearchOption : SearchEvent
    object Search : SearchEvent
    object LoadMore : SearchEvent

}
