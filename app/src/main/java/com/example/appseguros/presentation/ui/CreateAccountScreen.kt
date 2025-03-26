package com.example.appseguros.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DatePickerDialog
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import com.example.appseguros.ui.component.CustomTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.appseguros.ui.component.CustomButton
import com.example.appseguros.ui.theme.AppSegurosTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(
    navController: NavHostController,
    viewModel: CreateAccountViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        // Arrange elements from top to bottom
        verticalArrangement = Arrangement.Top
    ) {
        // Header Text
        Text(
            text = "Registro",
            fontSize = 24.sp,
            color = Color(183, 32, 0),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp) // Add some padding below the header
        )

        // Main Content
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val datePickerState = rememberDatePickerState()
            if (viewModel.showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { viewModel.showDatePicker = false },
                    confirmButton = {
                        Button(onClick = {
                            datePickerState.selectedDateMillis?.let {
                                viewModel.birthDate =
                                    Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault())
                                        .toLocalDate()
                                val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                viewModel.selectedDateText =
                                    viewModel.birthDate?.format(dateFormatter) ?: ""

                            }
                            viewModel.showDatePicker = false
                        }) {
                            Text(text = "OK")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            CustomTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                label = "Nombre",
                isError = viewModel.nameError
            )
            CustomTextField(
                value = viewModel.lastName,
                onValueChange = { viewModel.lastName = it },
                label = "Apellido Paterno",
                isError = viewModel.lastNameError
            )
            CustomTextField(
                value = viewModel.secondLastName,
                onValueChange = { viewModel.secondLastName = it },
                label = "Apellido Materno",
                isError = viewModel.secondLastNameError
            )

            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(14.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = viewModel.selectedDateText,
                        onValueChange = { },
                        modifier = Modifier.weight(1f),
                        readOnly = true
                    )
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Select Date",
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { viewModel.showDatePicker = true }
                    )
                }
            }
            CustomTextField(
                value = viewModel.phoneNumber,
                onValueChange = { viewModel.phoneNumber = it },
                label = "Numero de Telefono",
                keyboardType = KeyboardType.Phone,
                isError = viewModel.phoneNumberError
            )
            CustomTextField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                label = "Correo",
                keyboardType = KeyboardType.Email,
                isError = viewModel.emailError
            )
            CustomTextField(
                value = viewModel.pass,
                onValueChange = { viewModel.pass = it },
                label = "Contraseña",
                isPasswordTextField = true,
                isError = viewModel.passError
            )
            CustomTextField(
                value = viewModel.passConfirm,
                onValueChange = { viewModel.passConfirm = it },
                label = "Confirmar Contraseña",
                isPasswordTextField = true,
                isError = viewModel.passError
            )

            Spacer(modifier = Modifier.height(16.dp))
            if (viewModel.isLoading) {
                CircularProgressIndicator()
            } else {
                CustomButton(text = "Crear Cuenta", onClick = {
                    if (viewModel.validateFields()) {
                        viewModel.createUser()
                    }
                })
            }

            LaunchedEffect(key1 = viewModel.isSuccess) {
                if (viewModel.isSuccess) {
                    viewModel.clear()
                    navController.popBackStack()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CreateAccountScreenPreview() {
    AppSegurosTheme {
        CreateAccountScreen(navController = rememberNavController())
    }
}

