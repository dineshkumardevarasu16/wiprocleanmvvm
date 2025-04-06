package com.observer.apiimplementation.presentationLayer.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.observer.apiimplementation.dataLayer.repository.OfflineDatabaseConnect
import com.observer.apiimplementation.dataLayer.repository.OfflineDatabaseManager
import com.observer.apiimplementation.application.ProductApplication.Companion.context

object HelperUtils {

    var connectivityManager: ConnectivityManager? = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val offlineDatabase : OfflineDatabaseManager? = context?.let { OfflineDatabaseConnect.initializeDatabase(it) }0

    fun isNetworkAvailable() : Boolean
    {
        return connectivityManager?.let { it.getNetworkCapabilities(it.activeNetwork)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true } ?: false
    }
}