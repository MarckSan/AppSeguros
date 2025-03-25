package com.example.appseguros.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appseguros.data.model.User
import com.example.appseguros.domain.use_case.LoginUserUseCase
import com.example.appseguros.domain.use_case.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

enum class LoginError {
    WRONG_PASSWORD, USER_NOT_FOUND, NETWORK_ERROR, NONE
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    var email by mutableStateOf("")
    var pass by mutableStateOf("")
    var user by mutableStateOf<User?>(null)
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun login() {
        viewModelScope.launch {
            isLoading = true
            isError = false
            //user = loginUserUseCase(email, pass)
            isLoading = false
            if(user == null){
                isError = true
            }
        }
    }
    fun createUser(name: String, lastName: String, secondLastName: String, birthDate: LocalDate, phoneNumber: String, email: String, pass: String){
        viewModelScope.launch {
            isLoading = true
            isError = false
            val user = User(
                id = (1..100).random(),
                name = name,
                lastName = lastName,
                secondLastName = secondLastName,
                birthDate = birthDate,
                phoneNumber = phoneNumber,
                email = email,
                pass = pass
            )
            saveUserUseCase(user)
            isLoading = false
        }
    }
    fun clear(){
        email = ""
        pass = ""
    }
}