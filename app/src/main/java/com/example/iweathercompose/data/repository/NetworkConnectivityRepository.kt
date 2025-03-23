package com.example.iweathercompose.data.repository

import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import com.example.iweathercompose.screens.NetworkConnectivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

interface NetworkConnectivityRepository {
    val networkConnectivityState : StateFlow<NetworkConnectivityState>
}

class DefaultNetworkConnectivityRepository @Inject constructor(private val connectivityManager: ConnectivityManager): NetworkConnectivityRepository{
    private val _networkConnectivityState = MutableStateFlow<NetworkConnectivityState>(NetworkConnectivityState.UnAvailable)

    private val callback = object : NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            _networkConnectivityState.value = NetworkConnectivityState.Available
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            _networkConnectivityState.value = NetworkConnectivityState.UnAvailable
        }
    }

    override val networkConnectivityState: StateFlow<NetworkConnectivityState> = _networkConnectivityState.asStateFlow()

    init {
        connectivityManager.registerDefaultNetworkCallback(callback)
    }
}