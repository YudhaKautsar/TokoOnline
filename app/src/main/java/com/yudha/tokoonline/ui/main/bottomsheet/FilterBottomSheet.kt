package com.yudha.tokoonline.ui.main.bottomsheet

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.setMargins
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yudha.tokoonline.R
import com.yudha.tokoonline.databinding.FragmentFilterBottomSheetBinding

class FilterBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilterBottomSheetBinding
    private var listener: OnSelectedListener? = null
    private lateinit var title: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMargin(view)
        context?.let {


            binding.lblCategory.setOnClickListener {
                listener?.onCategory()
                dismiss()
            }
            binding.lblProfile.setOnClickListener {
                listener?.onProfile()
                dismiss()
            }

        }
    }

    private fun addMargin(view: View) {
        (view.parent as View).setBackgroundResource(R.drawable.bg_bottom_bar_rounded)
        (view.parent.parent as View).apply {

            (layoutParams as FrameLayout.LayoutParams).apply {
            setMargins(16.toPx().toInt())
        }
        }
    }


    private fun Number.toPx() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

    fun showBottomSheet(
        manager: FragmentManager,
        title: String?,
        listener: OnSelectedListener
    ) {
        if (!isVisible){
            show(manager, tag)
            if (title != null) {
                this.title = title
            }
            this.listener = listener
        }
    }

    interface OnSelectedListener {
        fun onCategory()
        fun onProfile()
    }
}