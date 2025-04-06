package com.observer.apiimplementation.presentationLayer.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.observer.apiimplementation.presentationLayer.viewmodel.AddProductViewModel
import com.observer.apiimplementation.R
import com.observer.apiimplementation.presentationLayer.utils.HelperUtils
import com.observer.apiimplementation.domainLayer.utils.NetworkStatus
import com.observer.apiimplementation.databinding.ActivityAdddataBinding
import com.observer.apiimplementation.domainLayer.model.response.LoginResponse
import com.observer.apiimplementation.domainLayer.utils.UserResource
import com.observer.apiimplementation.presentationLayer.utils.HelperUtils.isNetworkAvailable
import kotlinx.coroutines.launch

class AddProductActivity : AppCompatActivity() {

    private val mViewModel : AddProductViewModel by viewModels<AddProductViewModel>()

    private lateinit var mBinding : ActivityAdddataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this@AddProductActivity,
            R.layout.activity_adddata
        )

        mBinding.viewmodels = mViewModel
        mBinding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()

        mViewModel.resultLogin.observe(this@AddProductActivity) { dataResponse -> dataResponse.getDataLogin() }

        mViewModel.lambdaExpression = {

            if (isNetworkAvailable()) {

                mViewModel.loading.value = true
                mViewModel.addProductData()
            }
        }
    }

    private fun UserResource<LoginResponse>.getDataLogin()
    {
        when(this)
        {
            is UserResource.Success -> {
                mViewModel.loading.value = false
                Toast.makeText(this@AddProductActivity, "Login Success token $data", Toast.LENGTH_LONG).show()
            }

            is UserResource.Error -> {
                mViewModel.loading.value = false
                Toast.makeText(this@AddProductActivity, "Login Failed token $message", Toast.LENGTH_LONG).show()
            }

            is UserResource.Loading -> {
                mViewModel.loading.value = true
            }
        }
    }
}