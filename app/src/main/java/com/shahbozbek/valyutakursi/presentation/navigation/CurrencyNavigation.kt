package com.shahbozbek.valyutakursi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shahbozbek.valyutakursi.presentation.ui.components.ConversionScreen
import com.shahbozbek.valyutakursi.presentation.ui.main.screen.MainScreen

@Composable
fun CurrencyNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "mainScreen") {
        composable("mainScreen") { MainScreen(navController) }
        composable("conversionScreen/{rateJson}") { backStackEntry ->
            val rateJson = backStackEntry.arguments?.getString("rateJson")
            ConversionScreen(
                navController = navController,
                rateJson = rateJson
            )
        }
    }

}