package com.example.appseguros.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.appseguros.data.model.ContractedInsurance
import com.example.appseguros.presentation.viewModel.ContractedInsurancesViewModel

@Composable
fun ContractedInsurancesScreen(navController: NavHostController, viewModel: ContractedInsurancesViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Seguros Contratados")
        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else if (viewModel.isError){
            Text(text = "Error al cargar los seguros")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.insurances) { insurance ->
                    InsuranceItem(insurance, onCancelClick = { viewModel.cancelInsurance(insurance.id!!) })
                }
            }
        }
    }
}

@Composable
fun InsuranceItem(insurance: ContractedInsurance, onCancelClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "Nombre del Seguro: ${insurance.productId}")
        Text(text = "Fecha de Contratación: ${insurance.endDate}")
        Button(onClick = onCancelClick) {
            Text(text = "Cancelar Seguro")
        }
    }
}