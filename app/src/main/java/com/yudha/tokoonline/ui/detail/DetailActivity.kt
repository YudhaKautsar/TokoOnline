package com.yudha.tokoonline.ui.detail

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.yudha.tokoonline.R
import com.yudha.tokoonline.api.model.localDatabase.dao.ProductDao
import com.yudha.tokoonline.api.model.localDatabase.table.ProductEntity
import com.yudha.tokoonline.api.model.response.ProductResponse
import com.yudha.tokoonline.base.BaseActivity
import com.yudha.tokoonline.base.Constant
import com.yudha.tokoonline.databinding.ActivityDetailBinding
import com.yudha.tokoonline.databinding.LayoutFragmentToolbarBinding
import com.yudha.tokoonline.ui.detail.bottomsheet.SinglePickerRecyclerProductBottomSheet
import com.yudha.tokoonline.ui.main.MainActivity
import com.yudha.tokoonline.util.RoundedTransformation
import com.yudha.tokoonline.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var toolbarBinding: LayoutFragmentToolbarBinding
    private val detailViewModel: DetailViewModel by viewModels()
    val sheet = SinglePickerRecyclerProductBottomSheet()

    @Inject
    lateinit var productDao: ProductDao


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


        val productId = intent.getStringExtra(Constant.ID)

        productId?.let { detailViewModel.getProductDetail(it) }

        observeLoadingState(detailViewModel)

        detailViewModel.productDetail.observe(this) { product ->

            Glide.with(binding.fotoBarang)
                .load(product.image)
                .into(binding.fotoBarang)
            /*if(product.image.isNotEmpty()){
                Picasso.get()
                    .load(product.image)
                    .into(binding.fotoBarang, object : Callback {
                        override fun onSuccess() {
                            binding.fotoBarang.setPadding(0, 0, 0, 0)
                        }

                        override fun onError(e: Exception?) {
                            e?.message?.let { message -> Log.d("Picasso Error", message) }
                            binding.fotoBarang.setImageResource(R.drawable.ic_file)
                            binding.fotoBarang.setPadding(5, 5, 5, 5)
                        }
                    })
            }else {
                binding.fotoBarang.setImageResource(R.drawable.ic_file)
                binding.fotoBarang.setPadding(5, 5, 5, 5)
            }*/
            binding.namaBarang.text = product.title
            binding.hargaBarang.text = getString(R.string.lbl_price, product.price)
            binding.descBarang.text = product.description
            binding.btnKeranjang.setOnClickListener {
                sheet.listener = object:  SinglePickerRecyclerProductBottomSheet.OnSelectedListener{
                    override fun onSubmitCart(jumlahOrder: String?, data: ProductResponse?) {
                        sheet.dismiss()
                        AlertDialog.Builder(this@DetailActivity)
                            .setTitle("Success Added to Cart")
                            .setCancelable(false)
                            .setPositiveButton("Ok".toUpperCase()) { a, b ->
                                data?.let { productResponse ->
                                    val productEntity = ProductEntity(
                                        id = productResponse.id,
                                        title = productResponse.title,
                                        price = productResponse.price,
                                        image = productResponse.image,
                                        quantity = jumlahOrder?.toIntOrNull() ?: 0 // Konversi jumlahOrder ke Int atau default 1
                                    )

                                    lifecycleScope.launch {  // Pastikan operasi database ada dalam coroutine
                                        try {
                                            productDao.insertProduct(productEntity)  // Menyisipkan data ke database
                                        } catch (e: Exception) {
                                            Log.e("DetailActivity", "Error inserting product: ${e.message}")
                                        }
                                    }
                                }
                            }
                            .create().show()

                    }

                }
                sheet.showBottomSheet(supportFragmentManager, product.title, product)
            }

        }

        // Observe error messages
        detailViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}