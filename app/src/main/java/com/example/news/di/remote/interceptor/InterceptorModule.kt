package com.example.news.di.remote.interceptor

import com.example.news.data.remote.news.interceptor.NewsInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterceptorModule {

    @Binds
    @Singleton
    abstract fun bindsNewsInterceptor(newsInterceptor: NewsInterceptor): Interceptor
}