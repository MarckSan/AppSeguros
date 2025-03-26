package com.example.appseguros.data.model

import java.time.LocalDate

data class ContractedInsurance(
    var id: String? = null,
    val userId: String? = null,
    val productId: String? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    val policyPdfUrl: String? = null,
    var isCancelled: Boolean = false
){
    constructor() : this(null, null, null, null, null, null, false)
}