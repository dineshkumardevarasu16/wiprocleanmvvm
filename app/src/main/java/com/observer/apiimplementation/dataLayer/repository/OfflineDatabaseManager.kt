package com.observer.apiimplementation.dataLayer.repository

import android.content.Context
import com.observer.apiimplementation.dataLayer.model.ProductData

interface OfflineDatabaseManager {

    fun initializeDatabase(context : Context)

    fun insertDataOfProduct(productData: List<ProductData>)

    fun getDataProduct() : List<ProductData>

}