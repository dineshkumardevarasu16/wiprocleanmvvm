package com.observer.apiimplementation.presentationLayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.observer.apiimplementation.dataLayer.source.remote.APIBuilder
import com.observer.apiimplementation.domainLayer.usecase.APIHelperImpl
import com.observer.apiimplementation.dataLayer.source.remote.APIInterface
import com.observer.apiimplementation.domainLayer.model.input.LoginData
import com.observer.apiimplementation.domainLayer.model.response.LoginResponse
import com.observer.apiimplementation.domainLayer.model.response.Product
import com.observer.apiimplementation.domainLayer.usecase.CommonAPIViewModel
import com.observer.apiimplementation.domainLayer.utils.UserResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AddProductViewModel : ViewModel() {

    private val apiInterface : APIInterface = APIBuilder.getInitializeAPI("https://fakestoreapi.com/")
    val loginData : LoginData = LoginData(username = "mor_2314", password = "83r5^_")
    var commonAPIViewModel : CommonAPIViewModel = CommonAPIViewModel(APIHelperImpl(apiInterface))

    var productTitle : MutableLiveData<String> = MutableLiveData()
    var description : MutableLiveData<String> = MutableLiveData()
    var priceProduct : MutableLiveData<String> = MutableLiveData()

    var lambdaExpression : (() -> Unit)? = null

    var mediatorData : MediatorLiveData<Boolean> = MediatorLiveData(false)
    var loading : MutableLiveData<Boolean> = MutableLiveData(false)

    private val _response = MutableLiveData<UserResource<LoginResponse>>()
    val resultLogin: LiveData<UserResource<LoginResponse>> = _response

    init { checkValidation() }

    private fun checkValidation()
    {
        mediatorData.addSource(productTitle) { checkValidationData() }
        mediatorData.addSource(description) { checkValidationData()  }
        mediatorData.addSource(priceProduct) { checkValidationData() }
    }

    private fun checkValidationData()
    {
        val checkValid = !productTitle.value.isNullOrEmpty() && !description.value.isNullOrEmpty() && !priceProduct.value.isNullOrEmpty()
        mediatorData.value = checkValid
    }

    fun submitDataValue() = lambdaExpression?.invoke()

    fun addProductData() {

        viewModelScope.launch(Dispatchers.IO) { commonAPIViewModel.getLoginData(loginData).collectLatest { resultData -> _response.postValue(resultData) } }
    }
}