package com.example.news.domain.data

import com.example.news.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface RestCountriesLocalDataSource {

    val countries: Flow<List<Country>>
    fun insertCountries(countries: List<Country>)
}