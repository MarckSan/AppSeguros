package com.example.appseguros.presentation.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
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
import java.time.format.DateTimeFormatter
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
    var showErrorEmail by mutableStateOf(false)
    var showErrorPass by mutableStateOf(false)


    fun login() {
        // Verifica si los campos están vacíos y actualiza las variables de error
        if (email.isEmpty()) {
            showErrorEmail = true
        } else {
            showErrorEmail = false
        }

        if (pass.isEmpty()) {
            showErrorPass = true
        } else {
            showErrorPass = false
        }

        // Si hay un error en algún campo, no procede con el login
        if (showErrorEmail || showErrorPass) {
            return
        }
        viewModelScope.launch {
            isLoading = true
            //user = loginUseCase(email, pass)
            isLoading = false
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun createUser(name: String, lastName: String, secondLastName: String, birthDate: LocalDate, phoneNumber: String, email: String, pass: String){
        viewModelScope.launch {
            isLoading = true
            isError = false
            val dateString = birthDate?.format(DateTimeFormatter.ISO_DATE)
            val user = User(
                name = name,
                lastName = lastName,
                secondLastName = secondLastName,
                birthDate = dateString,
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