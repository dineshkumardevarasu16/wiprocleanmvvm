package com.observer.apiimplementation.dataLayer.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.observer.apiimplementation.dataLayer.model.ProductData

@Dao
interface ProductDataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductData(productData: List<ProductData>)

    @Query("SELECT * FROM PRODUCTDATATABLE")
    fun getProductAlData() : List<ProductData>

}