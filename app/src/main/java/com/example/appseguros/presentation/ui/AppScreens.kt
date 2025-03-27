package com.example.appseguros.presentation.ui

sealed class AppScreens(val route: String){
    object LoginScreen: AppScreens("login_screen")
    object CreateAccountScreen: AppScreens("create_account_screen")
    object ProductsScreen: AppScreens("products_screen")
    object ProductInfoScreen: AppScreens("product_info_screen")
    object ContractInsuranceScreen: AppScreens("contract_insurance_screen")
    object PdfScreen: AppScreens("pdf_screen")
    object ContractedInsurancesScreen: AppScreens("contracted_insurances_screen")
    object CancelledInsurancesScreen: AppScreens("cancelled_insurances_screen")
}