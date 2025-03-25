package com.example.appseguros.presentation.viewModel

import androidx.activity.result.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appseguros.data.model.InsuranceProduct
import com.example.appseguros.domain.use_case.GetInsuranceProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val getInsuranceProductsUseCase: GetInsuranceProductsUseCase) :
    ViewModel() {
    var products by mutableStateOf<List<InsuranceProduct>>(emptyList())
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            isLoading = true
            isError = false
            try {
                products = getInsuranceProductsUseCase()
            } catch (e: Exception) {
                isError = true
            }
            isLoading = false
        }
    }
}