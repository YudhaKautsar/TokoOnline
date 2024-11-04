package com.yudha.tokoonline.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.yudha.tokoonline.R
import com.yudha.tokoonline.base.BaseActivity
import com.yudha.tokoonline.databinding.ActivityMainBinding
import com.yudha.tokoonline.databinding.LayoutFragmentToolbarBinding
import com.yudha.tokoonline.ui.login.LoginActivity
import com.yudha.tokoonline.util.gone
import com.yudha.tokoonline.util.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbarBinding: LayoutFragmentToolbarBinding
    private val mainViewModel: MainViewModel by viewModels()

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
            // Observe the loading state from MainViewModel
            observeLoadingState(mainViewModel)

            // Fetch products or other actions that might trigger loading
            mainViewModel.fetchProducts()
        }
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Close MainActivity
    }
}