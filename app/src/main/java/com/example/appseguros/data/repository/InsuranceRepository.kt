package com.example.appseguros.data.repository

import com.example.appseguros.data.model.ContractedInsurance
import com.example.appseguros.data.model.InsuranceProduct
import com.example.appseguros.data.model.PdfData
import com.example.appseguros.data.model.User

interface InsuranceRepository {
    suspend fun getInsuranceProducts(): List<InsuranceProduct>
    suspend fun getContractedInsurances(userId: Int): List<ContractedInsurance>
    suspend fun getCancelledInsurances(userId: Int): List<ContractedInsurance>
    suspend fun saveUser(user: User): Boolean
    suspend fun loginUser(email: String, pass:String): User?
    suspend fun getPdfUrl(): PdfData
    suspend fun contractInsurance(userId: Int, productId: Int)
    suspend fun cancelInsurance(insuranceId: String)
}