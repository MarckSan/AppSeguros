package com.example.appseguros.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appseguros.presentation.ui.AppScreens
import com.example.appseguros.presentation.ui.LoginScreen
import com.example.appseguros.presentation.ui.*
import com.google.gson.Gson
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(AppScreens.CreateAccountScreen.route) {
            CreateAccountScreen(navController)
        }
        composable(AppScreens.ProductsScreen.route) {
            ProductsScreen(navController)
        }
        composable(
            route = AppScreens.ProductInfoScreen.route + "/{product}",
            arguments = listOf(navArgument("product") { type = NavType.StringType })
        ) { backStackEntry ->
            val productJson = backStackEntry.arguments?.getString("product")
            productJson?.let {
                ProductInfoScreen(navController, Gson().fromJson(it, com.example.appseguros.data.model.InsuranceProduct::class.java))
            }
        }
        composable(AppScreens.ContractInsuranceScreen.route){
            ContractInsuranceScreen(navController)
        }
        composable(AppScreens.PdfScreen.route){
            PdfScreen(navController)
        }
        composable(AppScreens.ContractedInsurancesScreen.route){
            ContractedInsurancesScreen(navController)
        }
        composable(AppScreens.CancelledInsurancesScreen.route){
            CancelledInsurancesScreen(navController)
        }
    }
}