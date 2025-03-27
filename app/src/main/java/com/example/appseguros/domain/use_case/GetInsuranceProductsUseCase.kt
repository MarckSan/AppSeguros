package com.example.appseguros.domain.use_case

import com.example.appseguros.data.model.InsuranceProduct
import com.example.appseguros.data.repository.InsuranceRepository
import javax.inject.Inject

class GetInsuranceProductsUseCase @Inject constructor(private val repository: InsuranceRepository) {
    suspend operator fun invoke(): List<InsuranceProduct> {
        return repository.getInsuranceProducts()
    }
}