package com.example.appseguros.domain.use_case

import com.example.appseguros.data.repository.InsuranceRepository
import javax.inject.Inject
import com.example.appseguros.data.model.ContractedInsurance as ContractedInsurance1

class GetContractedInsurancesUseCase @Inject constructor(private val repository: InsuranceRepository) {
    suspend operator fun invoke(userId: Int): List<ContractedInsurance1> {
        return repository.getContractedInsurances(userId)
    }
}