package com.example.news.di.remote

import android.app.Application
import com.example.news.data.remote.news.NewsWebservice
import com.example.news.data.remote.rest_countries.RestCountriesWebservice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        application: Application,
        newsInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(newsInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(NewsWebservice.BASE_URL)
            .build()


    @Provides
    @Singleton
    fun provideNewsWebService(
        okHttpClient: OkHttpClient,
        builder: Retrofit.Builder
    ): NewsWebservice =
        builder.client(okHttpClient)
            .baseUrl(NewsWebservice.BASE_URL)
            .build()
            .create(NewsWebservice::class.java)


    @Provides
    @Singleton
    fun provideRestCountriesWebService(builder: Retrofit.Builder): RestCountriesWebservice =
        builder.baseUrl(RestCountriesWebservice.BASE_URL)
            .build()
            .create(RestCountriesWebservice::class.java)
}