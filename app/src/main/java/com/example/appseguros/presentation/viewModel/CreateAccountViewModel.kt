package com.example.appseguros.presentation.viewModel

import androidx.activity.result.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appseguros.data.model.User
import com.example.appseguros.domain.use_case.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import kotlin.text.isBlank

@HiltViewModel
class CreateAccountViewModel @Inject constructor(private val saveUserUseCase: SaveUserUseCase) :
    ViewModel(){
    var name by mutableStateOf("")
    var lastName by mutableStateOf("")
    var secondLastName by mutableStateOf("")
    var birthDate by mutableStateOf<LocalDate?>(null)
    var phoneNumber by mutableStateOf("")
    var email by mutableStateOf("")
    var pass by mutableStateOf("")
    var passConfirm by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var isSuccess by mutableStateOf(false)

    fun createUser(){
        viewModelScope.launch {
            isLoading = true
            isError = false
            isSuccess = false
            val user = User(
                id = (1..100).random(), //Deberia ser autoincrementable
                name = name,
                lastName = lastName,
                secondLastName = secondLastName,
                birthDate = birthDate!!,
                phoneNumber = phoneNumber,
                email = email,
                pass = pass
            )
            val success = saveUserUseCase(user)
            isLoading = false
            if (success) {
                isSuccess = true
            } else {
                isError = true
            }
        }
    }

    fun validateFields(): Boolean {
        if (name.isBlank() ||
            lastName.isBlank() ||
            secondLastName.isBlank() ||
            birthDate == null ||
            phoneNumber.isBlank() ||
            email.isBlank() ||
            pass.isBlank() ||
            passConfirm.isBlank())
        {
            return false
        }

        if (pass != passConfirm){
            return false
        }

        return true
    }

    fun clear(){
        name = ""
        lastName = ""
        secondLastName = ""
        birthDate = null
        phoneNumber = ""
        email = ""
        pass = ""
        passConfirm = ""
    }
}