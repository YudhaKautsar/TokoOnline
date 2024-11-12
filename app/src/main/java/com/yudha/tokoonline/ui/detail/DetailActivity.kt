package com.yudha.tokoonline.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.yudha.tokoonline.R
import com.yudha.tokoonline.base.BaseActivity
import com.yudha.tokoonline.base.Constant
import com.yudha.tokoonline.databinding.ActivityDetailBinding
import com.yudha.tokoonline.databinding.LayoutFragmentToolbarBinding
import com.yudha.tokoonline.ui.detail.bottomsheet.SinglePickerRecyclerProductBottomSheet
import com.yudha.tokoonline.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var toolbarBinding: LayoutFragmentToolbarBinding
    private val detailViewModel: DetailViewModel by viewModels()
    val sheet = SinglePickerRecyclerProductBottomSheet()

    companion object {
        fun getIntent(
            context: Context,
            id: String?
        ) = Intent(context, DetailActivity::class.java
        ).apply {
            putExtra(Constant.ID, id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbarBinding = LayoutFragmentToolbarBinding.inflate(layoutInflater)
        val toolbar = toolbarBinding.toolbar
        setSupportActionBar(toolbar)
        binding.coordinatorLayout.addView(toolbarBinding.root)
        toolbarBinding.apply {
            toolbarTitle.text = getString(R.string.lbl_home)
            imgBack.visible()
            imgBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

        }

        binding.layoutKeranjang.setOnClickListener {

        }

        val productId = intent.getStringExtra(Constant.ID)

        productId?.let { detailViewModel.getProductDetail(it) }

        observeLoadingState(detailViewModel)

        detailViewModel.productDetail.observe(this) { product ->

            binding.namaBarang.text = product.title
            binding.hargaBarang.text = getString(R.string.lbl_price, product.price)
            binding.descBarang.text = product.description
        }

        // Observe error messages
        detailViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}