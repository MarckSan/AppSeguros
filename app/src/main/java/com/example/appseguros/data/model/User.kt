package com.example.appseguros.data.model

import java.time.LocalDate

data class User(
    var id: String? = null,
    val name: String? = null,
    val lastName: String? = null,
    val secondLastName: String? = null,
    val birthDate: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val pass: String? = null,
){
    constructor() : this(null, null, null, null, null, null, null, null)
}