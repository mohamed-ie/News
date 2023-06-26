package com.example.news.data.local.data_store

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

interface DataStoreKeys {
    companion object {
        val SOURCES = stringSetPreferencesKey("SOURCES")
        val COUNTRY_CODE = stringPreferencesKey("COUNTRY_CODE")
        val PAGE_SIZE = intPreferencesKey("PAGE_SIZE")
        val AUTO_REFRESH = booleanPreferencesKey("AUTO_REFRESH")
        val REFRESH_MILLS = intPreferencesKey("REFRESH_MILLS")
        val IS_FIRST_TIME = booleanPreferencesKey("IS_FIRST_TIME")
    }
}