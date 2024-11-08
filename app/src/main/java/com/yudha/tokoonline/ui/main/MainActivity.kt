package com.yudha.tokoonline.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yudha.tokoonline.R
import com.yudha.tokoonline.base.BaseActivity
import com.yudha.tokoonline.base.Constant
import com.yudha.tokoonline.databinding.ActivityMainBinding
import com.yudha.tokoonline.databinding.LayoutFragmentToolbarBinding
import com.yudha.tokoonline.ui.detail.DetailActivity
import com.yudha.tokoonline.ui.login.LoginActivity
import com.yudha.tokoonline.ui.main.adapter.ProductAdapter
import com.yudha.tokoonline.util.gone
import com.yudha.tokoonline.util.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbarBinding: LayoutFragmentToolbarBinding
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        if (!isUserLoggedIn()) {
            redirectToLogin()
        } else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            toolbarBinding = LayoutFragmentToolbarBinding.inflate(layoutInflater)
            val toolbar = toolbarBinding.toolbar
            setSupportActionBar(toolbar)
            binding.coordinatorLayout.addView(toolbarBinding.root)
            toolbarBinding.apply {
                toolbarTitle.text = getString(R.string.lbl_home)
                imgBack.gone()
                imgMenuFilter.visible()
                imgMenuFilter.setOnClickListener {

                }

            }
            setupRecyclerView()
            setupObservers()
            // Observe the loading state from MainViewModel
            observeLoadingState(mainViewModel)

            // Fetch products or other actions that might trigger loading
            mainViewModel.fetchProducts()
        }
    }

    private fun setupRecyclerView() {
        val gridManager = GridLayoutManager(this, 2)
        gridManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewProducts.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewProducts.adapter = productAdapter
    }

    private fun setupObservers() {
        mainViewModel.products.observe(this) { productList ->

            productAdapter.submitList(productList)
            productAdapter.setOnItemClickListener { product ->
                startActivity(
                    DetailActivity.getIntent(
                        context = this,
                        id = product.id.toString()
                    )
                )
            }
        }

        // Optionally observe loading and error states if needed
        mainViewModel.loading.observe(this) {
            observeLoadingState(mainViewModel)
        }

        mainViewModel.error.observe(this) { errorMessage ->
            // Show error message, e.g., in a Toast
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Close MainActivity
    }
}