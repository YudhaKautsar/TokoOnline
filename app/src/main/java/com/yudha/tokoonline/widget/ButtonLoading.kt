package com.yudha.tokoonline.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.yudha.tokoonline.R
import com.yudha.tokoonline.databinding.ViewLoadingButtonBinding

class ButtonLoading @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var binding: ViewLoadingButtonBinding =
        ViewLoadingButtonBinding.inflate(LayoutInflater.from(context), this, true)

    var isClickAble: Boolean = true
        set(value) {
            field = value
        }

    var text: String
        get() = binding.txtLabel.text.toString()
        set(value) {
            binding.txtLabel.text = value
        }

    var subText: String
        get() = binding.txtSubLabel.text.toString()
        set(value) {
            binding.txtSubLabel.text = value
            binding.txtSubLabel.visibility = if (value.isNotEmpty()) View.VISIBLE else View.GONE
        }

    var isLoading: Boolean
        get() = binding.pbLoading.visibility == View.VISIBLE
        set(value) {
            if (value) {
                binding.txtLabel.visibility = View.GONE
                binding.txtSubLabel.visibility = View.GONE
                binding.pbLoading.visibility = View.VISIBLE
                binding.layoutMain.isClickable = false
            } else {
                binding.txtLabel.visibility = View.VISIBLE
                binding.txtSubLabel.visibility = View.VISIBLE
                binding.pbLoading.visibility = View.GONE
                binding.layoutMain.isClickable = true
            }
        }

    var style: Int = 1
        set(value) {
            when (value) {
                2 -> {
                    binding.layoutMain.setBackgroundResource(R.drawable.bg_button_oval_full_netral)
                    binding.txtLabel.setTextColor(
                        ContextCompat.getColor(context, R.color.shade_of_gray)
                    )
                    binding.pbLoading.indeterminateDrawable.setColorFilter(
                        ContextCompat.getColor(context, R.color.white),
                        PorterDuff.Mode.SRC_IN
                    )
                }
                3 -> {
                    binding.layoutMain.setBackgroundResource(R.drawable.bg_button_oval_carolina_blue)
                    binding.txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                    binding.pbLoading.indeterminateDrawable.setColorFilter(
                        ContextCompat.getColor(context, R.color.white),
                        PorterDuff.Mode.SRC_IN
                    )
                }
                // Add other styles as needed...
                else -> {
                    binding.layoutMain.setBackgroundResource(R.drawable.bg_button_oval_full_netral)
                    binding.txtLabel.setTextColor(
                        ContextCompat.getColor(context, R.color.black_500)
                    )
                    binding.pbLoading.indeterminateDrawable.setColorFilter(
                        ContextCompat.getColor(context, R.color.white),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
            field = value
        }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        binding.txtLabel.isEnabled = enabled
        if (enabled) {
            isLoading = isLoading
        } else {
            binding.pbLoading.visibility = View.GONE
        }
    }

    private var listener: OnClickListener? = null

    override fun setOnClickListener(l: OnClickListener?) {
        listener = l
    }

    init {
        // Inflating the view with ViewBinding
        // No need to inflate here as it's done in the binding initialization
        isLoading = false
        isClickAble = true
        attrs?.let {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.ButtonLoading)

            isLoading = ta.getBoolean(R.styleable.ButtonLoading_isLoading, false)
            text = ta.getString(R.styleable.ButtonLoading_android_text) ?: ""
            subText = ""
            isClickAble = ta.getBoolean(R.styleable.ButtonLoading_isClickAble, true)
            style = ta.getInt(R.styleable.ButtonLoading_ietStyle, 1)

            binding.layoutMain.setOnClickListener {
                if (isClickAble)
                    listener?.onClick(this)
            }

            ta.recycle()
        }
    }
}