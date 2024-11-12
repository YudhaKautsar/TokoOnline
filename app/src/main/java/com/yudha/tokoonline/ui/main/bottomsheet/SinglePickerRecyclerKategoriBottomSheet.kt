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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yudha.tokoonline.base.adapter.RecyclerViewItemClickListener
import com.yudha.tokoonline.databinding.FragmentRecyclerKategoriPickerSheetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SinglePickerRecyclerKategoriBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentRecyclerKategoriPickerSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerKategoriPickerSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var adapter: SinglePickerRecyclerKategoriBottomSheetAdapter? = null
    private lateinit var data: List<String>

    var listener: OnSelectedListener? = null
    private lateinit var title: String
    private var selectedIndex = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMargin(view)


        context?.let {
            adapter = SinglePickerRecyclerKategoriBottomSheetAdapter(it)
            adapter?.listener = object : RecyclerViewItemClickListener<String> {
                override fun itemClick(
                    position: Int,
                    item: String?,
                    viewId: Int,
                    view: View?,
                    string: String?,
                    isFile: Boolean

                ) {
                    item?.let { data ->
                        listener?.onSelect(data, position)
                        dismiss()
                    }
                }
            }
            if (selectedIndex > -1){
                adapter?.selectedItemPos = selectedIndex
                adapter?.lastItemSelectedPos = selectedIndex
            }
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(it)
            binding.txtTitle.text = title
            adapter?.clear()
            adapter?.addAll(ArrayList(data))
        }
    }

    private fun addMargin(view: View) {
        ((view.parent.parent as View).layoutParams as FrameLayout.LayoutParams).setMargins(
            16.toPx().toInt()
        )
    }


    private fun Number.toPx() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

    fun showBottomSheet(
        manager: FragmentManager,
        data: List<String>,
        title: String?,
        selectedIndex: Int? = null
    ) {
        if (!isVisible){
            show(manager, tag)
            this.data = data
            if (title != null) {
                this.title = title
            }
            if (selectedIndex != null){
                this.selectedIndex = selectedIndex
            }
        }
    }

    interface OnSelectedListener {
        fun onSelect(data: String, position: Int)
    }
}