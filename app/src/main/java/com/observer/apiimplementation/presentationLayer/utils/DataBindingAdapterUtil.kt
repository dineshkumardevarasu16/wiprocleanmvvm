package com.observer.apiimplementation.presentationLayer.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.observer.apiimplementation.presentationLayer.adapter.ProductDataAdapter

object DataBindingAdapterUtil {

    @JvmStatic
    @BindingAdapter("productData")
    fun setProductList(recyclerView: RecyclerView, productData : List<String>?)
    {
        recyclerView.adapter?.takeIf { !productData.isNullOrEmpty() }?.let {
            val adapter = recyclerView.adapter as? ProductDataAdapter
            adapter?.submitList(productData)
        }
    }

    @JvmStatic
    @BindingAdapter("adapterData")
    fun setProductAdapter(recyclerView: RecyclerView, productDataAdapter: ProductDataAdapter)
    {
        recyclerView.apply {
            layoutManager = GridLayoutManager(recyclerView.context, 2)
            adapter = productDataAdapter
        }
    }
}