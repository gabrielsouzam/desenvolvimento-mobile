package com.gabriel.carapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabriel.carapp.ui.screens.CarScreen
import com.gabriel.carapp.ui.screens.HomeScreen
import com.gabriel.carapp.models.carList

@ExperimentalMaterial3Api
@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onCarSelected = { car ->
                navController.navigate("car/${car.model}")
            })
        }
        composable("car/{car}") { backStackEntry ->
            val carModel = backStackEntry.arguments?.getString("car")
            val selectedCar = carList.first { it.model == carModel }
            CarScreen(selectedCar)
        }
    }
}
