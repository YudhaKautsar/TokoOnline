package com.yudha.tokoonline.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yudha.tokoonline.R
import com.yudha.tokoonline.api.model.response.ProductResponse
import com.yudha.tokoonline.databinding.ItemIstationaryCategoryListBinding

class ProductAdapter: ListAdapter<ProductResponse, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    private var onItemClick: ((ProductResponse) -> Unit)? = null

    fun setOnItemClickListener(listener: (ProductResponse) -> Unit) {
        onItemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemIstationaryCategoryListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // Change to inner class so it can access outer class' members (like onItemClick)
    inner class ProductViewHolder(private val binding: ItemIstationaryCategoryListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductResponse) {
            // Bind product title
            binding.lblName.text = product.title

            // Bind product price
            binding.lblLihatLainnya.text = binding.root.context.getString(R.string.lbl_price, product.price)

            // Load product image with Glide
            Glide.with(binding.cardPhoto.context)
                .load(product.image)
                .into(binding.cardPhoto)

            binding.root.setOnClickListener {
                onItemClick?.invoke(product) // Correctly invoke the listener
            }
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<ProductResponse>() {
        override fun areItemsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean {
            return oldItem == newItem
        }
    }
}