package com.example.news.data.remote.rest_countries

import com.example.news.di.IODispatcher
import com.example.news.domain.data.RestCountriesRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RestCountriesRemoteDataSourceImpl @Inject constructor(
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val webservice: RestCountriesWebservice
) : RestCountriesRemoteDataSource {

    override fun getCountries(codes: String): Flow<Any> =
        flow {
            val response = webservice.getCountries(codes)
            emit(response)
        }.flowOn(ioDispatcher)

}