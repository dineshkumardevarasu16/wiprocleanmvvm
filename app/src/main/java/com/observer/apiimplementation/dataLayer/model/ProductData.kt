package com.observer.apiimplementation.dataLayer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "productDataTable"
)
data class ProductData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id : Int? = null,
    @ColumnInfo(name = "productName")
    val productName: String? = null
)

