package com.example.news.data.repository

import com.example.news.di.DefaultDispatcher
import com.example.news.domain.data.RemoteErrorHandler
import com.example.news.domain.data.RestCountriesRemoteDataSource
import com.example.news.domain.model.Country
import com.example.news.domain.data.mapper.CountryMapper
import com.example.news.domain.repository.RestCountriesRepository
import com.example.news.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RestCountriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RestCountriesRemoteDataSource,
    private val remoteErrorHandler: RemoteErrorHandler,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val mapper: CountryMapper
) : RestCountriesRepository {

    override fun getCountries(codes: String): Flow<Resource<List<Country>>> =
        remoteDataSource.getCountries(codes)
            .map { dto ->
                var countries = dto as List<Any>
                countries = countries.map(mapper::map)
                Resource.Success(countries) as Resource<List<Country>>
            }
            .catch { remoteErrorHandler.handle(it, this) }
            .flowOn(defaultDispatcher)

}


