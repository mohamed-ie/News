package com.example.news.domain.repository

import com.example.news.domain.model.Country
import com.example.news.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RestCountriesRepository {
    fun getCountries(codes: String): Flow<Resource<List<Country>>>
}