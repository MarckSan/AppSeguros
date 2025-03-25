package com.example.appseguros.presentation.viewModel

import androidx.activity.result.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appseguros.data.model.PdfData
import com.example.appseguros.domain.use_case.GetPdfUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PdfViewModel @Inject constructor(private val getPdfUrlUseCase: GetPdfUrlUseCase) :
    ViewModel() {
    var pdfData by mutableStateOf<PdfData?>(null)
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    init {
        getPdfData()
    }

    private fun getPdfData() {
        viewModelScope.launch {
            isLoading = true
            isError = false
            try {
                pdfData = getPdfUrlUseCase()
            } catch (e: Exception) {
                isError = true
            }
            isLoading = false
        }
    }
}