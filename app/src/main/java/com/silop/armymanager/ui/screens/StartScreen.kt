package com.silop.armymanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.silop.armymanager.addDummyData
import com.silop.armymanager.data.models.Army
import com.silop.armymanager.ui.navigation.Screen
import com.silop.armymanager.viewmodels.ArmyViewModel

@Composable
fun StartScreen(navController: NavController, viewModel: ArmyViewModel) {
    val armies = viewModel.armies.collectAsState(emptyList()).value

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Army Manager", style = MaterialTheme.typography.titleLarge)
            if (armies.isEmpty()) {
                EmptyScreen(navController, viewModel)
            } else {
                NormalScreen(navController, viewModel, armies)
            }

        }
    }
}

@Composable
fun EmptyScreen(
    navController: NavController, viewModel: ArmyViewModel
) {
    Text(
        text = "Looks like you have no armies", style = MaterialTheme.typography.bodyMedium
    )
    ElevatedButton(
        onClick = {
            addDummyData(viewModel)
            viewModel.loadArmy("Admech Dummy Army")
            navController.navigate(Screen.ArmyScreen.route)
        }
    ) {
        Text(text = "Start here")
    }
}

@Composable
fun NormalScreen(navController: NavController, viewModel: ArmyViewModel, armies: List<Army>) {
    val openDialog = remember { mutableStateOf(false) }

    val newArmyName = remember { mutableStateOf("") }

    armies.forEach {
        ElevatedButton(onClick = {
            viewModel.loadArmy(it.name)
            navController.navigate(Screen.ArmyScreen.route)
        }) {
            Text(
                text = it.name
            )
        }
    }
    ElevatedButton(
        onClick = { openDialog.value = true }
    ) {
        Text(
            text = "New army"
        )
    }
    if (openDialog.value) {
        AlertDialog(
            title = {},
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                ElevatedButton(
                    onClick = {
                        viewModel.addArmy(Army(name = newArmyName.value))
                        viewModel.loadArmy(newArmyName.value)
                        navController.navigate(Screen.ArmyScreen.route)
                    }
                ) {
                    Text(text = "Add ${newArmyName.value}")
                }
            },
            text = {
                OutlinedTextField(
                    value = newArmyName.value,
                    onValueChange = { newArmyName.value = it },
                    singleLine = true,
                    placeholder = { Text("Army Name") },
                )
            }
        )
    }
}