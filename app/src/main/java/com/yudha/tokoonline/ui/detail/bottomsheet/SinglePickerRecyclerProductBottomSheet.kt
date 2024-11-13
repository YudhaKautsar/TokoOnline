package com.yudha.tokoonline.ui.detail.bottomsheet

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.setMargins
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.yudha.tokoonline.R
import com.yudha.tokoonline.api.model.response.ProductResponse
import com.yudha.tokoonline.databinding.FragmentRecyclerPickerSheetProductBinding
import com.yudha.tokoonline.util.RoundedTransformation

class SinglePickerRecyclerProductBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentRecyclerPickerSheetProductBinding
    var listener: OnSelectedListener? = null
    private lateinit var title: String
    private var data: ProductResponse? = null

    private var itemCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerPickerSheetProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMargin(view)
        data?.let { setData(it) }
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

    private fun setData(data: ProductResponse){
        this.data = data
        data.let { item ->

            itemCount = 0
            binding.tvJumlah.text = itemCount.toString()
            binding.lblTitle.text = item.title

            if(itemCount == 0){
                binding.btnMin.setImageResource((R.drawable.ic_min_disabled))
                binding.btnMax.setImageResource((R.drawable.ic_max))
            }

            if(item.image.isNotEmpty()){
                val radius = 15
                Picasso.get()
                    .load(item.image)
                    .transform(RoundedTransformation(radius))
                    .into(binding.cardPhoto, object : Callback {
                        override fun onSuccess() {
                            binding.cardPhoto.setPadding(0, 0, 0, 0)
                        }

                        override fun onError(e: Exception?) {
                            e?.message?.let { message -> Log.d("Picasso Error", message) }
                            binding.cardPhoto.setImageResource(R.drawable.ic_file)
                            binding.cardPhoto.setPadding(5, 5, 5, 5)
                        }
                    })
            }else {
                binding.cardPhoto.setImageResource(R.drawable.ic_file)
                binding.cardPhoto.setPadding(5, 5, 5, 5)
            }



            //            btn_tambah.setOnClickListener {
            //                Toast.makeText(requireContext(), "please choose type", Toast.LENGTH_SHORT).show()
            //            }
            //
            //            btn_kirim.setOnClickListener {
            //                Toast.makeText(requireContext(), "please choose type", Toast.LENGTH_SHORT).show()
            //            }

            binding.btnMax.setOnClickListener {
                itemCount += 1
                binding.tvJumlah.text = itemCount.toString()
                binding.btnMin.setImageResource(R.drawable.ic_min)
            }

            binding.btnMin.setOnClickListener {
                //                    Log.d(TAG, "bind: $clickCount == $amount")
                if (itemCount > 0) {
                    itemCount -= 1
                    binding.tvJumlah.text = itemCount.toString()
                    if (itemCount == 0) {
                        binding.btnMin.setImageResource(R.drawable.ic_min_disabled)
                    }
                }

            }

            binding.btnKeranjang.setOnClickListener {
                if (itemCount == 0){
                    Toast.makeText(context, context?.getString(R.string.lbl_warning_amount), Toast.LENGTH_SHORT).show()
                }else {
                    listener?.onSubmitCart(binding.tvJumlah.text.toString(), item)
                }
            }

            binding.lblTitle.text = title
        }
    }


    fun showBottomSheet(
        manager: FragmentManager,
        title: String?,
        data: ProductResponse
    ) {
        if (!isVisible){

            if (title != null) {
                this.title = title
            }
            this.data = data
            show(manager, tag)
        }
    }

    interface OnSelectedListener {
        fun onSubmitCart(jumlahOrder: String?, data: ProductResponse?)
    }
}