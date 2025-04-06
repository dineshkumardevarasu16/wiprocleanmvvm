package com.observer.apiimplementation.application

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

class ProductApplication : Application() {

    companion object {

        private var weakContext: WeakReference<Context>? = null

        val context: Context? get() = weakContext?.get()
    }

    override fun onCreate() {
        super.onCreate()

        weakContext = WeakReference(applicationContext)

    }
}