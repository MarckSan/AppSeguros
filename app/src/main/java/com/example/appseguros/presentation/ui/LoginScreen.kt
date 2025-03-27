package com.example.appseguros.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.error
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.appseguros.R
import com.example.appseguros.presentation.viewModel.LoginError
import com.example.appseguros.presentation.viewModel.LoginViewModel
import com.example.appseguros.ui.component.CustomButton
import com.example.appseguros.ui.component.CustomTextField

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_logo_app),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(220.dp)
                .padding(bottom = 20.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Login", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = "Email",
            keyboardType = KeyboardType.Email,
            isError = viewModel.showErrorEmail
        )

        CustomTextField(
            value = viewModel.pass,
            onValueChange = { viewModel.pass = it },
            label = "Contraseña",
            isPasswordTextField = true,
            isError = viewModel.showErrorPass
        )

        // Manejo del error
       /* when (LoginError.values()) {
            LoginError.WRONG_PASSWORD -> {
                Text(text = "Contraseña incorrecta")
            }
            LoginError.USER_NOT_FOUND -> {
                Text(text = "Usuario no encontrado")
            }
            LoginError.NETWORK_ERROR -> {
                Text(text = "Error de red")
            }
            LoginError.NONE -> {}
        }*/
        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            CustomButton(text = "Iniciar Sesión") {
                viewModel.login()
            }
        }
        // Navegacion
        if (viewModel.user != null) {
            LaunchedEffect(Unit) {
                viewModel.clear()
                navController.navigate(AppScreens.ProductsScreen.route)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "¿No tienes cuenta? Crea una aquí",
            modifier = Modifier.clickable {
                navController.navigate(AppScreens.CreateAccountScreen.route)
            }
        )
    }
}