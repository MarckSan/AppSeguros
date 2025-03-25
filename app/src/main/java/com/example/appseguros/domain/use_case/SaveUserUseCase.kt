package com.example.appseguros.domain.use_case

import com.example.appseguros.data.model.User
import com.example.appseguros.data.repository.InsuranceRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val repository: InsuranceRepository) {
    suspend operator fun invoke(user: User): Boolean {
        return repository.saveUser(user)
    }
}