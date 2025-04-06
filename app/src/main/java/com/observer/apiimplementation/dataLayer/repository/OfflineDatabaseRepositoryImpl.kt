package com.observer.apiimplementation.dataLayer.repository

import android.content.Context
import com.observer.apiimplementation.dataLayer.model.ProductData
import com.observer.apiimplementation.dataLayer.source.local.ProductRoomDatabase

class OfflineDatabaseRepositoryImpl : OfflineDatabaseManager {

    private var productRoomDatabase : ProductRoomDatabase? = null

    companion object
    {
        private val instance : OfflineDatabaseRepositoryImpl by lazy { OfflineDatabaseRepositoryImpl() }

        fun initializeDatabaseRepo() : OfflineDatabaseRepositoryImpl
        {
            return instance
        }
    }

    override fun initializeDatabase(context: Context) {
       productRoomDatabase = ProductRoomDatabase.initializeDatabase(context)
    }

    override fun insertDataOfProduct(productData: List<ProductData>) {
        productRoomDatabase?.getProductDao()?.insertProductData(productData)
    }

    override fun getDataProduct(): List<ProductData> {
        return productRoomDatabase?.getProductDao()?.getProductAlData() ?: emptyList()
    }
}