package com.example.news.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val pageSize: Flow<Int>
    val refreshMills: Flow<Int>
    val countryCode: Flow<String>
    val isFirstTime: Flow<Boolean>
    val autoRefresh: Flow<Boolean>

    fun updateCountry(code: String)
    fun updatePageSize(size: Int)
    fun updateRefreshMills(value: Int)
    fun updateAutoRefresh(value:Boolean)

}