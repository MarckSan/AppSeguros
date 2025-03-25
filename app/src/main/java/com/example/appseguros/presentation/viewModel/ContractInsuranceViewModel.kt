package com.example.appseguros.presentation.viewModel

import androidx.activity.result.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appseguros.domain.use_case.ContractInsuranceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContractInsuranceViewModel @Inject constructor(private val contractInsuranceUseCase: ContractInsuranceUseCase) :
    ViewModel() {
    var userId by mutableStateOf(0)
    var productId by mutableStateOf(0)
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var isSuccess by mutableStateOf(false)

    fun contractInsurance() {
        viewModelScope.launch {
            isLoading = true
            isError = false
            isSuccess = false
            try {
                contractInsuranceUseCase(userId, productId)
                isSuccess = true
            } catch (e: Exception) {
                isError = true
            }
            isLoading = false
        }
    }
}