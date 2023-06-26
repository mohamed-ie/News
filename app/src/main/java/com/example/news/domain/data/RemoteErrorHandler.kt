package com.example.news.domain.data

import com.example.news.utils.Resource
import kotlinx.coroutines.flow.FlowCollector

interface RemoteErrorHandler {
    suspend fun <D> handle(throwable: Throwable, collector: FlowCollector<Resource<D>>)
}