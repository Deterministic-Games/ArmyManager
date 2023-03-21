package com.silop.armymanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.silop.armymanager.viewmodels.ArmyViewModel
import com.silop.armymanager.ui.screens.*

@Composable
fun Navigation(viewModel: ArmyViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.StartScreen.route
    ) {
        composable(route = Screen.StartScreen.route) {
            StartScreen(navController, viewModel)
        }
        composable(route = Screen.ArmyScreen.route) {
            ArmyScreen(armyViewModel = viewModel)
        }
    }
}
