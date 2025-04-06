package com.observer.apiimplementation.presentationLayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.observer.apiimplementation.presentationLayer.adapter.ProductDataAdapter
import com.observer.apiimplementation.dataLayer.source.remote.APIBuilder
import com.observer.apiimplementation.domainLayer.usecase.APIHelperImpl
import com.observer.apiimplementation.dataLayer.source.remote.APIInterface
import com.observer.apiimplementation.dataLayer.model.ProductData
import com.observer.apiimplementation.domainLayer.model.response.Product
import com.observer.apiimplementation.domainLayer.usecase.CommonAPIViewModel
import com.observer.apiimplementation.domainLayer.utils.UserResource
import com.observer.apiimplementation.presentationLayer.utils.HelperUtils.offlineDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val apiInterface : APIInterface = APIBuilder.getInitializeAPI("https://fakestoreapi.com/")

    val commonAPIViewModel : CommonAPIViewModel = CommonAPIViewModel(APIHelperImpl(apiInterface))

    var productDataAdapter: ProductDataAdapter = ProductDataAdapter(this@ProductsViewModel)

    var callProductApi : MutableLiveData<Boolean> = MutableLiveData(false)

    var networkAvailable : MutableLiveData<Boolean> = MutableLiveData(false)

    var productDataList : MutableLiveData<List<ProductData>> = MutableLiveData(mutableListOf())

    var productsData : MutableLiveData<List<String>> = MutableLiveData()

    private val _categories = MutableLiveData<UserResource<List<String>>>()
    val categories: LiveData<UserResource<List<String>>> = _categories

    private val _productCategories = MutableLiveData<UserResource<List<Product>>>()
    val productCategories: LiveData<UserResource<List<Product>>> = _productCategories

    var buttonOnClickNewProduct : (() -> Unit) ? = null

    var checkBoxlistener : ((Boolean,Int)  -> Unit)? = null

    fun addNewData() = buttonOnClickNewProduct?.invoke()

    fun callProductAPItoGetData()
    {
        viewModelScope.launch(Dispatchers.IO) { commonAPIViewModel.getProductsCategories().collectLatest { result -> _categories.postValue(result) } }
    }

    fun callCategoriesProduct(productName : String)
    {
        viewModelScope.launch(Dispatchers.IO) { commonAPIViewModel.getCategoriesProduct(productName).collectLatest { result -> _productCategories.postValue(result) }}
    }

    fun updateProductList(newData: List<String>?) {
        newData?.takeIf { it.isNotEmpty() }?.map { productName -> ProductData(productName = productName) }?.let { newProducts -> offlineDatabase?.insertDataOfProduct(newProducts) }
    }
}