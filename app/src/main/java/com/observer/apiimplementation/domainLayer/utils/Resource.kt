package com.observer.apiimplementation.domainLayer.utils

data class Resource<T>(val status: NetworkStatus, val data: T? = null, val message: String? = null)
{
    companion object
    {
        fun <T> success(data: T,message: String?) : Resource<T> = Resource(status= NetworkStatus.SUCCESS,data= data, message= message)

        fun <T> failed(data: T, message: String) : Resource<T> = Resource(status = NetworkStatus.FAILED,data = data, message = message)

        fun <T> loading(message: String) : Resource<T> =  Resource(status = NetworkStatus.LOADING, message = message)
    }
}
