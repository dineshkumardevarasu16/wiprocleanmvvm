package com.observer.apiimplementation.domainLayer.utils

sealed class UserResource<T>(
    val status: NetworkStatus,
    var data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T, message: String? = null) : UserResource<T>(status= NetworkStatus.SUCCESS, data = data, message = message)
    class Error<T>(message: String, data: T? = null) : UserResource<T>(status= NetworkStatus.FAILED, data = data, message = message)
    class Loading<T>(message: String) : UserResource<T>(status = NetworkStatus.LOADING,message = message)

    companion object {
        fun <T> success(data: T, message: String? = null): UserResource<T> = Success(data, message)
        fun <T> error(message: String, data: T? = null): UserResource<T> = Error(message, data)
        fun <T> loading(message: String) : UserResource<T> = Loading(message)
    }
}
