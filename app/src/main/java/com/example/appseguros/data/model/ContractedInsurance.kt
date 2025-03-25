package com.example.appseguros.data.model

import java.time.LocalDate

data class ContractedInsurance(
    val id: Int,
    val user: User,
    val product: InsuranceProduct,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val policyPdfUrl: String,
    var isCancelled: Boolean = false
)