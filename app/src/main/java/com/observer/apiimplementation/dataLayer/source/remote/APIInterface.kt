package com.observer.apiimplementation.dataLayer.source.remote

import com.observer.apiimplementation.domainLayer.model.input.LoginData
import com.observer.apiimplementation.domainLayer.model.response.LoginResponse
import com.observer.apiimplementation.domainLayer.model.response.Product
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIInterface {

    @GET("products/categories")
    suspend fun getProductsCategories() : List<String>

    @GET("products/category/{category}")
    suspend fun getCategoriesProduct(@Path("category") category: String) : List<Product>

    @POST("auth/login")
    suspend fun getLoginUser(@Body loginData: LoginData) : LoginResponse
}