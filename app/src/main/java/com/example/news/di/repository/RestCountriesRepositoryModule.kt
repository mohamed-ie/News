package com.example.news.di.repository

import com.example.news.data.mapper.CountryMapperImpl
import com.example.news.data.remote.rest_countries.RestCountriesRemoteDataSourceImpl
import com.example.news.data.repository.RestCountriesRepositoryImpl
import com.example.news.domain.data.RestCountriesRemoteDataSource
import com.example.news.domain.data.mapper.CountryMapper
import com.example.news.domain.repository.RestCountriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RestCountriesRepositoryModule {

    @Binds
    abstract fun bindsRestCountriesRepository(impl: RestCountriesRepositoryImpl): RestCountriesRepository

    @Binds
    abstract fun bindsRestCountriesRemoteDataSource(impl: RestCountriesRemoteDataSourceImpl): RestCountriesRemoteDataSource

    @Binds
    abstract fun bindsCountryMapper(impl: CountryMapperImpl): CountryMapper

}