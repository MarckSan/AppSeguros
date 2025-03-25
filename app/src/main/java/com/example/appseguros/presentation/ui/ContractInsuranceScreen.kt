package com.example.appseguros.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.appseguros.presentation.viewModel.ContractInsuranceViewModel
import com.example.appseguros.ui.component.CustomButton

@Composable
fun ContractInsuranceScreen(navController: NavHostController, viewModel: ContractInsuranceViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Contratación")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Datos del Asegurado")
        Spacer(modifier = Modifier.height(16.dp))
        //TODO: Aqui van los campos del usuario

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            CustomButton(text = "Contratar") {
                viewModel.contractInsurance()
            }
        }

        if (viewModel.isError){
            Text(text = "Hubo un error al contratar el seguro")
        }

        if (viewModel.isSuccess){
            LaunchedEffect(Unit){
                navController.navigate(AppScreens.PdfScreen.route)
            }
        }
    }
}