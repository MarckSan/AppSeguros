package com.example.appseguros.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appseguros.data.model.InsuranceProduct
import com.example.appseguros.presentation.viewModel.ProductInfoViewModel
import com.example.appseguros.ui.component.CustomButton
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProductInfoScreen(navController: NavHostController, insuranceProduct: InsuranceProduct, viewModel: ProductInfoViewModel = hiltViewModel()) {
    viewModel.setProduct(insuranceProduct)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        viewModel.product?.let { product ->
            Text(text = product.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Información del seguro", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = product.description)
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(text = "Solicitar Seguro") {
                navController.navigate(AppScreens.ContractInsuranceScreen.route)
            }
        }
    }
}