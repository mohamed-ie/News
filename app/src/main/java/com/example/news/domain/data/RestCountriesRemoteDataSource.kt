package com.example.news.domain.data

import kotlinx.coroutines.flow.Flow

interface RestCountriesRemoteDataSource {
    fun getCountries(codes: String): Flow<Any>
}