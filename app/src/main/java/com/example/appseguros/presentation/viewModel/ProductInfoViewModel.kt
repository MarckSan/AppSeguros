package com.example.appseguros.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.appseguros.data.model.InsuranceProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor() : ViewModel() {
    private var _product by mutableStateOf<InsuranceProduct?>(null)
    val product: InsuranceProduct?
        get() = _product

    fun setProduct(insuranceProduct: InsuranceProduct) {
        _product = insuranceProduct
    }
}