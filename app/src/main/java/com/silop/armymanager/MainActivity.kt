package com.silop.armymanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import com.silop.armymanager.database.ArmyDao
import com.silop.armymanager.database.MiniDao
import com.silop.armymanager.database.ArmyDatabase
import com.silop.armymanager.models.Army
import com.silop.armymanager.models.Miniature
import com.silop.armymanager.models.Weapon
import com.silop.armymanager.ui.theme.ArmyManagerTheme
import com.silop.armymanager.viewmodels.ArmyViewModel
import com.silop.armymanager.views.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import javax.inject.Inject



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val armyViewModel: ArmyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LaunchedEffect(Unit) {
                armyViewModel.loadArmies()
            }
            ArmyManagerTheme {
                Navigation(viewModel = armyViewModel)
            }
        }
    }
}

fun addDummyData(viewModel: ArmyViewModel) {
    val army = Army(name = "Admech Dummy Army")

    viewModel.addArmy(army)

    val rangers = MutableList(4) {
        Miniature(
            name = "Skitarii Ranger",
            basePoints = 8,
            unitName = "Skitarii Rangers",
            armyName = army.name
        )
    }
    rangers.add(Miniature(
        name = "Skitarii Alpha",
        basePoints = 8,
        unitName = "Skitarii Rangers",
        armyName = army.name
    ))
    rangers.forEach {
        it.equippedWeapons.add(Weapon("Galvanic Rifle", 0))
        viewModel.addMini(it)
    }

    val destroyers = List(3) {
        Miniature(
            name = "Kataphron Destroyer",
            basePoints = 40,
            unitName = "Kataphron Destroyers",
            armyName = army.name
        )
    }
    destroyers.forEach {
        it.equippedWeapons.add(Weapon("Kataphron plasma culverin", 10))
        viewModel.addMini(it)
    }
}

fun removeDummyData(viewModel: ArmyViewModel) {
    val army = viewModel.armies.value.find { it.name == "Admech Dummy Army" }

    if (army != null) {
        viewModel.deleteArmy(army)
    }
}