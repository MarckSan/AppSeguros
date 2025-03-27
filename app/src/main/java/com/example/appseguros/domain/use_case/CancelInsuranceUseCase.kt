package com.example.appseguros.domain.use_case

import com.example.appseguros.data.repository.InsuranceRepository
import javax.inject.Inject

class CancelInsuranceUseCase @Inject constructor(private val repository: InsuranceRepository) {
    suspend operator fun invoke(insuranceId: String) {
        repository.cancelInsurance(insuranceId)
    }
}