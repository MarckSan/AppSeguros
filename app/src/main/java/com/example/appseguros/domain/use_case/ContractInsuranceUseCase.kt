package com.example.appseguros.domain.use_case

import com.example.appseguros.data.repository.InsuranceRepository
import javax.inject.Inject

class ContractInsuranceUseCase @Inject constructor(private val repository: InsuranceRepository) {
    suspend operator fun invoke(userId: Int, productId: Int) {
        repository.contractInsurance(userId, productId)
    }
}