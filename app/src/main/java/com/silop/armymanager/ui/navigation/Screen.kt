package com.silop.armymanager.ui.navigation

sealed class Screen(val route: String) {
    object ArmyScreen : Screen("army_screen")
    object StartScreen : Screen("start_screen")
}