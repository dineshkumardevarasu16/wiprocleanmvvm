package com.observer.apiimplementation.domainLayer.usecase

import com.observer.apiimplementation.domainLayer.model.input.LoginData
import com.observer.apiimplementation.domainLayer.model.response.LoginResponse
import com.observer.apiimplementation.domainLayer.model.response.Product
import com.observer.apiimplementation.domainLayer.repository.APIHelper
import com.observer.apiimplementation.dataLayer.source.remote.APIInterface

class APIHelperImpl(private val apiInterface: APIInterface) : APIHelper {

    override suspend fun getProductsCategories(): List<String> = apiInterface.getProductsCategories()

    override suspend fun getCategoriesProduct(productName: String): List<Product> = apiInterface.getCategoriesProduct(productName)

    override suspend fun getLoginData(loginData: LoginData): LoginResponse = apiInterface.getLoginUser(loginData)
}