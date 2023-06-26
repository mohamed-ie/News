package com.example.news.data.remote

import com.example.news.domain.data.RemoteErrorHandler
import com.example.news.utils.Resource
import com.example.news.utils.UIError
import kotlinx.coroutines.flow.FlowCollector
import java.net.SocketTimeoutException
import javax.inject.Inject

class RemoteErrorHandlerImpl @Inject constructor() : RemoteErrorHandler {
    override suspend fun <D> handle(throwable: Throwable, collector: FlowCollector<Resource<D>>) {
        throwable.printStackTrace()
        when (throwable) {
            is SocketTimeoutException -> collector.emit(Resource.Error(UIError.CONNECTION_TIMEOUT))
            else -> collector.emit(Resource.Error(UIError.UN_EXPECTED_ERROR))
        }
    }
}