package com.observer.apiimplementation.presentationLayer.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.observer.apiimplementation.presentationLayer.viewmodel.ProductsViewModel
import com.observer.apiimplementation.R
import com.observer.apiimplementation.domainLayer.utils.NetworkStatus
import com.observer.apiimplementation.dataLayer.model.ProductData
import com.observer.apiimplementation.databinding.ActivityMainBinding
import com.observer.apiimplementation.presentationLayer.observer.NetworkObserver
import com.observer.apiimplementation.dataLayer.repository.OfflineDatabaseConnect
import com.observer.apiimplementation.dataLayer.repository.OfflineDatabaseManager
import com.observer.apiimplementation.dataLayer.repository.OfflineDatabaseRepositoryImpl
import com.observer.apiimplementation.domainLayer.model.response.Product
import com.observer.apiimplementation.domainLayer.utils.UserResource
import com.observer.apiimplementation.presentationLayer.utils.HelperUtils.isNetworkAvailable
import com.observer.apiimplementation.presentationLayer.utils.HelperUtils.offlineDatabase
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

class ProductsActivity : AppCompatActivity() {

    private val mViewModel : ProductsViewModel by viewModels<ProductsViewModel>()
    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this@ProductsActivity, R.layout.activity_main)

        mBinding.mViewModel = mViewModel
        mBinding.lifecycleOwner = this

    }

    override fun onStart() {
        super.onStart()

        val productNames: List<String>? = offlineDatabase?.getDataProduct()?.map { it.productName!! }

        if (isNetworkAvailable()) mViewModel.callProductAPItoGetData() else mViewModel.productsData.value = productNames

        NetworkObserver().observe(this@ProductsActivity) {
           mViewModel.networkAvailable.value = it
           if (!it)
           {
               Toast.makeText(this@ProductsActivity,"Network Disconnected",Toast.LENGTH_SHORT).show()
           }
        }

        mViewModel.buttonOnClickNewProduct = {
            val newProduct = Intent(this, AddProductActivity::class.java)
            startActivity(newProduct)
        }

        mViewModel.categories.observe(this@ProductsActivity) { it.getResultData() }
        mViewModel.productCategories.observe(this@ProductsActivity) { it.getResultProductData() }

        mViewModel.checkBoxlistener = { isChecked,position ->
           if (isChecked)
           {
               if (isNetworkAvailable()) { mViewModel.callCategoriesProduct(mViewModel.productsData.value?.get(position)!!) }
           }
        }

    }

    override fun onResume() {
        super.onResume()
    }

    private fun UserResource<List<String>>.getResultData()
    {
        when(this)
        {
            is UserResource.Success ->
            {
                mViewModel.productDataList.value = emptyList()
                mViewModel.updateProductList(data)
                mViewModel.productsData.value = data
            }
            is UserResource.Error ->
            {
                Toast.makeText(this@ProductsActivity,message, Toast.LENGTH_LONG).show()
            }

            is UserResource.Loading -> {
                Toast.makeText(this@ProductsActivity, "Loading...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun UserResource<List<Product>>.getResultProductData()
    {
        if (this.status == NetworkStatus.SUCCESS)
        {
            mViewModel.productDataList.value = emptyList()
        }
        else if (this.status == NetworkStatus.FAILED)
        {
            Toast.makeText(this@ProductsActivity,this.message, Toast.LENGTH_LONG).show()
        }
    }

}