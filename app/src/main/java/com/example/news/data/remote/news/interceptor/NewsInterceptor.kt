package com.example.news.data.remote.news.interceptor

import com.example.news.common.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NewsInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val urlBuilder = chain.request().url.newBuilder()
        requestBuilder.url(
            urlBuilder
                .addQueryParameter("apiKey", Constants.NEWS_API_KEY)
                .build()
        )
        return chain.proceed(requestBuilder.build())
    }
}