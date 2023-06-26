package com.example.news.di.local

import android.content.Context
import androidx.room.Room
import com.example.news.data.local.db.AppDatabase
import com.example.news.data.local.db.ArticlesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = AppDatabase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideArticlesDao(appDatabase: AppDatabase): ArticlesDao =
        appDatabase.dao
}