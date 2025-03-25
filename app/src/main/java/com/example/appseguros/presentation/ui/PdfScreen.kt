package com.example.appseguros.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.appseguros.presentation.viewModel.PdfViewModel

@Composable
fun PdfScreen(navController: NavHostController, viewModel: PdfViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Generando PDF...")
        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { viewModel.pdfData?.url?.let {  } },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ver PDF")
            }
        }

        if (viewModel.isError) {
            Text(text = "Error al generar el PDF")
        }

        if (viewModel.pdfData != null) {
            LaunchedEffect(Unit) {
                navController.navigate(AppScreens.ContractedInsurancesScreen.route)
            }
        }
    }
}