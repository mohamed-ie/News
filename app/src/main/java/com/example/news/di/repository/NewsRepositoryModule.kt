package com.example.news.di.repository

import com.example.news.data.local.NewsLocalDataSourceImpl
import com.example.news.data.remote.news.NewsRemoteDataSourceImpl
import com.example.news.data.repository.NewsRepositoryImpl
import com.example.news.domain.data.NewsLocalDataSource
import com.example.news.domain.data.NewsRemoteDataSource
import com.example.news.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsNewsRepositoryModule(newsRepositoryImpl: NewsRepositoryImpl):NewsRepository


    @Binds
    @Singleton
    abstract fun bindsNewsRemoteDataSource(remoteDataSource: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsNewsLocalDataSource(localDataSource: NewsLocalDataSourceImpl): NewsLocalDataSource


}