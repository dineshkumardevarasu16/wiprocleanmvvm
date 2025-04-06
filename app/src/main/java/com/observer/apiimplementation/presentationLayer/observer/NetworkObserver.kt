package com.observer.apiimplementation.presentationLayer.observer

import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import androidx.lifecycle.LiveData
import com.observer.apiimplementation.presentationLayer.utils.HelperUtils.connectivityManager
import com.observer.apiimplementation.presentationLayer.utils.HelperUtils.isNetworkAvailable

class NetworkObserver : LiveData<Boolean>() {

    private val networkCallback : NetworkCallback = object : NetworkCallback()
    {
        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(isNetworkAvailable())
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(isNetworkAvailable())
        }
    }

    override fun onActive() {
        super.onActive()
        postValue(isNetworkAvailable())
        connectivityManager?.registerDefaultNetworkCallback(networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }

}