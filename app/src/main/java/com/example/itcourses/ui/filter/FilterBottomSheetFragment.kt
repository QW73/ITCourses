package com.example.itcourses.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.itcourses.R
import com.example.itcourses.databinding.BottomSheetFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheetFragment(
    private val onApplyFilters: (String?, String?, String?) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetFilterBinding? = null
    private val binding get() = _binding!!

    private var selectedCategory: String? = null
    private var selectedDifficulty: String? = null
    private var selectedPrice: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetFilterBinding.inflate(inflater, container, false)

        setupFilters()

        binding.buttonApply.setOnClickListener {
            onApplyFilters(selectedCategory, selectedDifficulty, selectedPrice)
            dismiss()
        }

        binding.buttonReset.setOnClickListener {
            resetFilters()
        }

        return binding.root
    }

    private fun setupFilters() {
        // Type
        binding.radioGroupCategory.setOnCheckedChangeListener { _, checkedId ->
            selectedCategory = when (checkedId) {
                R.id.radioKotlin -> "kotlin"
                R.id.radioAndroidSDK -> "android_sdk"
                R.id.radioUIUX -> "ui_ux"
                else -> null
            }
        }

        // Difficylty
        binding.radioGroupDifficulty.setOnCheckedChangeListener { _, checkedId ->
            selectedDifficulty = when (checkedId) {
                R.id.radioBeginner -> "easy"
                R.id.radioIntermediate -> "normal"
                R.id.radioAdvanced -> "advanced"
                else -> null
            }
        }

        // Price
        binding.radioGroupPrice.setOnCheckedChangeListener { _, checkedId ->
            selectedPrice = when (checkedId) {
                R.id.radioFree -> "-"
                R.id.radioPaid -> "paid"
                else -> null
            }
        }
    }

    private fun resetFilters() {
        binding.radioGroupCategory.clearCheck()
        binding.radioGroupDifficulty.clearCheck()
        binding.radioGroupPrice.clearCheck()
        selectedCategory = null
        selectedDifficulty = null
        selectedPrice = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
