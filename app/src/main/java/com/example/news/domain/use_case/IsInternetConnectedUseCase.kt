package com.example.news.domain.use_case

import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsInternetConnectedUseCase @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val networkRequest: NetworkRequest
) {
    operator fun invoke() = callbackFlow {
        send(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null)

        connectivityManager.requestNetwork(networkRequest, object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(false)
            }
        })

        awaitClose{
//            connectivityManager.unregisterNetworkCallback(networkRequest)
        }
    }
}