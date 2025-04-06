package com.observer.apiimplementation.dataLayer.source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIBuilder {

    fun getInitializeAPI(baseURL : String, httpLoggingInterceptor : HttpLoggingInterceptor.Level? = null, converterFactory : Factory? = null) : APIInterface
    {
        return getClient(baseURL,httpLoggingInterceptor,converterFactory)
    }

    private fun getClient(baseURL : String, httpLoggingInterceptor : HttpLoggingInterceptor.Level? = null, converterFactory : Factory? = null) : APIInterface
    {
        return Retrofit.Builder().baseUrl(baseURL)
            .client(createClient(httpLoggingInterceptor ?: HttpLoggingInterceptor.Level.BODY))
            .addConverterFactory(converterFactory ?: GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)
    }

    private fun createClient(httpLoggingInterceptor: HttpLoggingInterceptor.Level) : OkHttpClient
    {
        val httpLoggingLevel = HttpLoggingInterceptor().setLevel(httpLoggingInterceptor)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(httpLoggingLevel)
        return okHttpClient.build()
    }
}