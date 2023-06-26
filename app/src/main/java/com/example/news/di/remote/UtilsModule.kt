package com.example.news.di.remote

import com.example.news.data.remote.RemoteErrorHandlerImpl
import com.example.news.domain.data.RemoteErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModule {

    @Binds
    @Singleton
    abstract fun bindsRemoteCallHandler(remoteCallHandlerImpl: RemoteErrorHandlerImpl): RemoteErrorHandler
}