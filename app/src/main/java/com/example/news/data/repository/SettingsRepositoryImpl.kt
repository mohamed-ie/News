package com.example.news.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.news.R
import com.example.news.data.local.data_store.DataStoreKeys
import com.example.news.di.ExternalScope
import com.example.news.di.IODispatcher
import com.example.news.domain.repository.SettingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val datastore: DataStore<Preferences>,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
    @ExternalScope
    private val externalScope: CoroutineScope,
) : SettingsRepository {


    override val countryCode: Flow<String> =
        datastore.data.map {
            it[DataStoreKeys.COUNTRY_CODE] ?: "eg"
        }.flowOn(ioDispatcher)

    override val pageSize: Flow<Int> =
        datastore.data.map {
            it[DataStoreKeys.PAGE_SIZE] ?: R.string.twenty
        }.flowOn(ioDispatcher)

    override val refreshMills: Flow<Int> =
        datastore.data.map {
            it[DataStoreKeys.REFRESH_MILLS] ?: R.string.every_five_minutes
        }.flowOn(ioDispatcher)

    override val autoRefresh: Flow<Boolean> =
        datastore.data.map {
            it[DataStoreKeys.AUTO_REFRESH] ?: false
        }.flowOn(ioDispatcher)


    override fun updateCountry(code: String) {
        externalScope.launch(ioDispatcher) {
            datastore.edit { it[DataStoreKeys.COUNTRY_CODE] = code }
        }
    }

    override fun updatePageSize(size: Int) {
        externalScope.launch(ioDispatcher) {
            datastore.edit { it[DataStoreKeys.PAGE_SIZE] = size }
        }
    }

    override fun updateRefreshMills(value: Int) {
        externalScope.launch(ioDispatcher) {
            datastore.edit { it[DataStoreKeys.REFRESH_MILLS] = value }
        }
    }

    override fun updateAutoRefresh(value: Boolean) {
        externalScope.launch(ioDispatcher) {
            datastore.edit { it[DataStoreKeys.AUTO_REFRESH] = value }
        }
    }


    override val isFirstTime: Flow<Boolean> =
        datastore.data.map {
            it[DataStoreKeys.COUNTRY_CODE] == null
        }.flowOn(ioDispatcher)


}