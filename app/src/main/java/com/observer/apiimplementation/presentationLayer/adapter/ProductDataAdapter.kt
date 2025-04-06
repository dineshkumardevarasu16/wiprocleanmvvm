package com.observer.apiimplementation.presentationLayer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.observer.apiimplementation.databinding.InflateProductlistviewBinding
import com.observer.apiimplementation.presentationLayer.viewmodel.ProductsViewModel

class ProductDataAdapter(private val mViewModel : ProductsViewModel) : ListAdapter<String, ProductDataAdapter.ProductViewHolder>(
    CustomDiffUtils()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val mBinding = InflateProductlistviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(mBinding,mViewModel)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productData = getItem(position)
        holder.bindData(productData)
    }

    class ProductViewHolder(
        private val mBinding: InflateProductlistviewBinding,
        private  val mViewModel: ProductsViewModel
    ) :
        RecyclerView.ViewHolder(mBinding.root)  {

        fun bindData(productData: String) {
            mBinding.productdata = productData
            mBinding.mViewModel = this
            mBinding.executePendingBindings()
        }

        fun onCheckedChangeCheckboxA(view: View, ischecked: Boolean) = mViewModel.checkBoxlistener?.invoke(ischecked,adapterPosition)

    }

    class CustomDiffUtils : DiffUtil.ItemCallback<String>()
    {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return false
        }

    }
}