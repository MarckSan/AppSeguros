package com.example.appseguros.domain.use_case

import com.example.appseguros.data.model.ContractedInsurance
import com.example.appseguros.data.repository.InsuranceRepository
import javax.inject.Inject

class GetCancelledInsurancesUseCase @Inject constructor(private val repository: InsuranceRepository) {
    suspend operator fun invoke(userId: Int): List<ContractedInsurance> {
        return repository.getCancelledInsurances(userId)
    }
}