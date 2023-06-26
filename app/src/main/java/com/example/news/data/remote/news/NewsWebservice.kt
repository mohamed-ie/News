package com.example.news.data.remote.news

import com.example.news.data.remote.news.dto.news.NewsDTO
import com.example.news.data.remote.news.dto.source.SourcesDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsWebservice {
    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("q") query: String,
        @Query("pageSize") pageSize: Byte,
        @Query("page") page: Int
    ): NewsDTO


    @GET("top-headlines/sources")
    suspend fun getSources(): SourcesDTO

    @GET("everything")
    suspend fun getEverything(
        @Query("sources") sources: String,
        @Query("q") query: String,
        @Query("searchIn") searchIn: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String,
        @Query("language") language: String,
        @Query("pageSize") pageSize: Byte,
        @Query("page") page: Int
    ): NewsDTO
}