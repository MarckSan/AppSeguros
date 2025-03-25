package com.example.appseguros.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DatePickerDialog
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.Alignment
import androidx.compose.material3.DatePicker
import androidx.compose.ui.Modifier
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appseguros.presentation.viewModel.CreateAccountViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import com.example.appseguros.ui.component.CustomTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appseguros.ui.component.CustomButton
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(navController: NavHostController, viewModel: CreateAccountViewModel = hiltViewModel()) {
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Crear Cuenta")
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(value = viewModel.name, onValueChange = { viewModel.name = it }, label = "Nombre")
        CustomTextField(value = viewModel.lastName, onValueChange = { viewModel.lastName = it }, label = "Apellido Paterno")
        CustomTextField(value = viewModel.secondLastName, onValueChange = { viewModel.secondLastName = it }, label = "Apellido Materno")

        Button(onClick = { showDatePicker = true }) {
            Text(text = "Seleccionar Fecha de Nacimiento")
        }
        Text(text = "Fecha seleccionada: ${selectedDate?.toString() ?: "Ninguna"}")
        CustomTextField(value = viewModel.phoneNumber, onValueChange = { viewModel.phoneNumber = it }, label = "Numero de Celular", keyboardType = KeyboardType.Phone)
        CustomTextField(value = viewModel.email, onValueChange = { viewModel.email = it }, label = "Email", keyboardType = KeyboardType.Email)
        CustomTextField(value = viewModel.pass, onValueChange = { viewModel.pass = it }, label = "Contraseña", isPasswordTextField = true)
        CustomTextField(value = viewModel.passConfirm, onValueChange = { viewModel.passConfirm = it }, label = "Confirmar Contraseña", isPasswordTextField = true)

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            CustomButton(text = "Crear Cuenta") {
                if (viewModel.validateFields()) {
                    viewModel.birthDate = selectedDate
                    viewModel.createUser()
                }
            }
        }

        if (viewModel.isError){
            Text(text = "Hubo un error al crear el usuario")
        }

        if (viewModel.isSuccess){
            LaunchedEffect(Unit){
                viewModel.clear()
                navController.navigate(AppScreens.LoginScreen.route)
            }
        }
        if (showDatePicker) {
            val datePickerState = rememberDatePickerState()
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    Button(onClick = {
                        showDatePicker = false
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        if (selectedDateMillis != null) {
                            selectedDate = Instant.ofEpochMilli(selectedDateMillis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                        }
                    }) {
                        Text(text = "Aceptar")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}