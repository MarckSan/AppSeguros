package com.example.appseguros.presentation.viewModel

import androidx.activity.result.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appseguros.data.model.ContractedInsurance
import com.example.appseguros.domain.use_case.GetCancelledInsurancesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CancelledInsurancesViewModel @Inject constructor(private val getCancelledInsurancesUseCase: GetCancelledInsurancesUseCase) :
    ViewModel() {
    var insurances by mutableStateOf<List<ContractedInsurance>>(emptyList())
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    init {
        getInsurances(0)//Se debe pasar el id del usuario
    }

    fun getInsurances(userId: Int) {
        viewModelScope.launch {
            isLoading = true
            isError = false
            try {
                insurances = getCancelledInsurancesUseCase(userId)
            } catch (e: Exception) {
                isError = true
            }
            isLoading = false
        }
    }
}