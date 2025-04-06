package com.observer.apiimplementation.dataLayer.source.local

import androidx.room.RoomDatabase

abstract class MainDatabase : RoomDatabase() {

    abstract fun getProductDao() : ProductDataDAO
}