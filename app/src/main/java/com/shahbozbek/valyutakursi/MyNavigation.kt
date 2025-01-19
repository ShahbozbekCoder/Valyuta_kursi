package com.shahbozbek.valyutakursi

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation() {
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