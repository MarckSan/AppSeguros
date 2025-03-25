package com.example.appseguros.domain.use_case

import com.example.appseguros.data.model.User
import com.example.appseguros.data.repository.InsuranceRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repository: InsuranceRepository) {
    suspend operator fun invoke(email: String, pass: String): User? {
        return repository.loginUser(email, pass)
    }
}