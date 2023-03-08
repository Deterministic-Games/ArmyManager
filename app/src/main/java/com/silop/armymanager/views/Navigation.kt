package com.silop.armymanager.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.silop.armymanager.viewmodels.ArmyViewModel

@Composable
fun Navigation(viewModel: ArmyViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "army_screen"
    ) {
        composable(route = "start_screen") {

        }
        composable(route = "army_screen") {
            ArmyScreen(armyViewModel = viewModel)
        }
    }
}