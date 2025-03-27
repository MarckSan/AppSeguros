package com.example.appseguros.data.model

data class InsuranceProduct(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "", // URL de la imagen
    val price: String = "",
    val type: String = ""
)

enum class InsuranceType {
    AUTO,
    HEALTH,
    PET,
    CELLULAR_PROTECTION,
    HOME,
    TRAVEL
}