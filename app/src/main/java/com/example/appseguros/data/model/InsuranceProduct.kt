package com.example.appseguros.data.model

data class InsuranceProduct(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String, // URL de la imagen
    val type: InsuranceType
)

enum class InsuranceType {
    AUTO,
    HEALTH,
    PET,
    CELLULAR_PROTECTION,
    HOME,
    TRAVEL
}