package com.example.appseguros.data.model

import java.time.LocalDate

data class User(
    val id: Int,
    val name: String,
    val lastName: String,
    val secondLastName: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val email: String,
    val pass: String,
)