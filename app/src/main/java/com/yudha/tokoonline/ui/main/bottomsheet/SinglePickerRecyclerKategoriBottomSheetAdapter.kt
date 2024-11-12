package com.yudha.tokoonline.ui.main.bottomsheet

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yudha.tokoonline.base.adapter.BaseAdapter
import com.yudha.tokoonline.base.adapter.BaseHolder
import com.yudha.tokoonline.base.adapter.RecyclerViewItemClickListener
import com.yudha.tokoonline.databinding.ItemRecyclerKategoriBottomSheetBinding


class SinglePickerRecyclerKategoriBottomSheetAdapter(val context: Context) :
    BaseAdapter<String, SinglePickerRecyclerKategoriBottomSheetAdapter.ViewHolder>() {

    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    companion object {
        const val ITEM_CLICKED = 0
    }

    override fun bindViewHolder(holder: ViewHolder, data: String?) {
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        listener, ItemRecyclerKategoriBottomSheetBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    inner class ViewHolder(
        listener: RecyclerViewItemClickListener<String>,
        private val binding: ItemRecyclerKategoriBottomSheetBinding
    ) :
        BaseHolder<String>(listener, binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(itemData: String?) {
            itemData?.let { data ->

                binding.lblTitle.text = data

                binding.root.setOnClickListener {
                    listener.itemClick(adapterPosition, data, ITEM_CLICKED)
                }

            }
        }

    }

}