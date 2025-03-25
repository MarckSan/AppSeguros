package com.example.appseguros.domain.use_case

import com.example.appseguros.data.model.PdfData
import com.example.appseguros.data.repository.InsuranceRepository
import javax.inject.Inject

class GetPdfUrlUseCase @Inject constructor(private val repository: InsuranceRepository) {
    suspend operator fun invoke(): PdfData {
        return repository.getPdfUrl()
    }
}