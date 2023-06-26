package com.example.news.ui.screens.search.view.component

import android.text.format.DateFormat
import com.example.news.R
import java.util.Calendar
import java.util.Date

data class ManageSearchState(
    val selectedLanguage: String = "all",
    val selectedSearchInOptions: List<Int> = listOf(
        R.string.title,
        R.string.description,
        R.string.content
    ),
    val selectedSortOption: Int = R.string.published_at,
    val startMills: Long = Calendar.getInstance().apply { add(Calendar.MONTH, -1) }.timeInMillis,
    val endMills: Long = Date().time,
    val fromDate: String = DateFormat.format(DATE_PATTERN, startMills).toString(),
    val toDate: String = DateFormat.format(DATE_PATTERN, endMills).toString(),
) {
    companion object {
        const val DATE_PATTERN = "yyyy MMM dd"
    }
}
