package com.yudha.tokoonline.ui.detail.bottomsheet

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    lateinit var title: String
    private var data: ProductResponse? = null

    var itemCount = 0
    var itemAmount = 0

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
        data?.let { setData(it) }
    }

    fun setData(data: ProductResponse){
        this.data = data
        data.let {

            itemCount = 0
            binding.tvJumlah.text = itemCount.toString()
            binding.lblTitle.text = it.title

            if(itemCount == 0){
                binding.btnMin.setImageResource((R.drawable.ic_min_disabled))
                binding.btnMax.setImageResource((R.drawable.ic_max))
            }

            if(it.image.isNotEmpty()){
                val radius = 15
                Picasso.get()
                    .load(it.image)
                    .resize(400,400).centerCrop()
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
                if (itemAmount in 1..itemAmount) {
                    binding.btnMin.setImageResource(R.drawable.ic_min)
                    itemCount += 1
                    itemAmount -= 1
                    binding.tvJumlah.text = itemCount.toString()
                    if (itemAmount == 0) {
                        binding.btnMax.setImageResource(R.drawable.ic_max_disabled)
    //                        btn_max.isEnabled = true
                    }

                }
            }

            binding.btnMin.setOnClickListener {
                //                    Log.d(TAG, "bind: $clickCount == $amount")
                binding.btnMax.setImageResource(R.drawable.ic_max)
                if (itemCount > 0) {
                    itemCount -= 1
                    itemAmount += 1
                    binding.tvJumlah.text = itemCount.toString()
                    if (itemCount == 0) {
                        binding.btnMin.setImageResource(R.drawable.ic_min_disabled)
                    }
                }

            }

            binding.lblTitle.text = title
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener?.onDismis(data)
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
        fun onAdd(data: ProductResponse)
        fun onDismis(data: ProductResponse?)

        fun onSubmitCart(jumlahOrder: String?, unixTipe: String?)
        fun onSend(jumlahOrder: String?, unixTipe: String?)
    }
}