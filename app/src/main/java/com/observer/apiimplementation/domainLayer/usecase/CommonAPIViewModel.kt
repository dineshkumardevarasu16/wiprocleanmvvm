package com.observer.apiimplementation.domainLayer.usecase

import com.observer.apiimplementation.domainLayer.repository.APIHelper
import com.observer.apiimplementation.domainLayer.utils.UserResource
import com.observer.apiimplementation.domainLayer.model.input.LoginData
import com.observer.apiimplementation.domainLayer.model.response.LoginResponse
import com.observer.apiimplementation.domainLayer.model.response.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CommonAPIViewModel(private val apiHelper: APIHelper) {

    fun getProductsCategories(): Flow<UserResource<List<String>>> = flow {
        val response = apiHelper.getProductsCategories()
        emit(UserResource.success(data = response, message = "Fetch Data from API success"))
    }.catch { exception ->
        emit(UserResource.error(data = null, message = exception.localizedMessage ?: "Unknown error"))
    }

    fun getCategoriesProduct(productName: String) : Flow<UserResource<List<Product>>> = flow {
        val response = apiHelper.getCategoriesProduct(productName)
        emit(UserResource.success(data = response, message = "Fetch Data Product from API Success"))
    }.catch {
        emit(UserResource.error(data = null, message = it.localizedMessage ?: "Unknown error"))
    }

    fun getLoginData(loginData: LoginData) : Flow<UserResource<LoginResponse>> = flow {
        val response = apiHelper.getLoginData(loginData)
        emit(UserResource.success(data = response, message = "Login Success"))
    }.catch {
        emit(UserResource.error(data = null, message = it.localizedMessage ?: "Unknown error"))
    }

   /* fun getProductsCategories() = liveData(Dispatchers.IO)
    {
        emit(UserResource.loading())
        try
        {
           emit(UserResource.success(data = apiHelper.getProductsCategories(), message = "Fetch Data from API success"))
        }
        catch (exception : Exception)
        {
            emit(UserResource.error(data = null, message = exception.localizedMessage ?: ""))
        }
    }*/

    /*fun getCategoriesProduct(productname : String) = liveData(Dispatchers.IO) {

        emit(UserResource.loading())
        try {
            emit(UserResource.success(data = apiHelper.getCategoriesProduct(productname), message = ""))
        }
        catch (exception : Exception)
        {
            emit(UserResource.error(data = null, message = exception.localizedMessage ?: ""))
        }
    }*/

    /*fun getLoginData(loginData: LoginData) = liveData(Dispatchers.IO)
    {
        emit(UserResource.Loading)
        try {
          emit(UserResource.success(data = apiHelper.getLoginData(loginData), message = "Login Success"))
        }
        catch (exception : Exception)
        {
            emit(UserResource.error(data = null, message = exception.localizedMessage ?: ""))
        }
    }*/
}