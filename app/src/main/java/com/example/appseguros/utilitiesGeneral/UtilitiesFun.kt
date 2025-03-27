package com.example.appseguros.utilitiesGeneral


object UtilitiesFun {
    fun extractUsernameSplit(email: String): String {
        val parts = email.split("@")
        return if (parts.isNotEmpty()) {
            parts[0]
        } else {
            "" // O el valor que quieras usar si no hay "@"
        }
    }
}