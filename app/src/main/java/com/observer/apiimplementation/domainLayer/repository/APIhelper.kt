package com.observer.apiimplementation.domainLayer.repository

import com.observer.apiimplementation.domainLayer.model.input.LoginData
import com.observer.apiimplementation.domainLayer.model.response.LoginResponse
import com.observer.apiimplementation.domainLayer.model.response.Product

interface APIHelper {

    suspend fun getProductsCategories() : List<String>

    suspend fun getCategoriesProduct(productName: String) : List<Product>

    suspend fun getLoginData(loginData: LoginData) : LoginResponse
}