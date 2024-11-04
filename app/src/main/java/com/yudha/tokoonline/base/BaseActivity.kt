package com.yudha.tokoonline.base

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.yudha.tokoonline.R
import com.yudha.tokoonline.result.token.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var tokenManager: TokenManager

    var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarColor(R.color.white, false)
    }
    fun observeLoadingState(viewModel: BaseViewModel) {
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) {
                showProgress()
            } else {
                hideProgress()
            }
        }
    }
    fun isUserLoggedIn(): Boolean {
        return tokenManager.isUserLoggedIn()
    }

    open fun changeStatusBarColor(color: Int, isDark: Boolean) {
        if (!isDark){
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(this, color)
        }
        else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE
        }
    }

    fun showProgress() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog?.show()
            if (progressDialog?.window != null) {
                progressDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            progressDialog?.setContentView(R.layout.layout_progress_dialog)
            progressDialog?.isIndeterminate = true
            progressDialog?.setCancelable(false)
            progressDialog?.setCanceledOnTouchOutside(false)
        } else {
            //animation_view.visibility = View.GONE
            progressDialog?.show()
        }
    }

    fun hideProgress() {
        progressDialog?.dismiss()
    }
}