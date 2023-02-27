package com.silop.armymanager.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.silop.armymanager.viewmodels.ArmyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.silop.armymanager.models.Army
import com.silop.armymanager.models.Unit
import com.silop.armymanager.models.Miniature
import com.silop.armymanager.models.Weapon


@Composable
fun ArmyScreen(
    modifier: Modifier = Modifier,
    armyViewModel: ArmyViewModel = viewModel()
) {
    val armyState by armyViewModel.armyState.collectAsState()

    // A unit of skitarii rangers
    val m1 = Miniature("Ranger Alpha", 8)
    val m2 = Miniature("Skitarii Ranger", 8)
    val m3 = Miniature("Skitarii Ranger", 8)
    val m4 = Miniature("Skitarii Ranger", 8)
    val m5 = Miniature("Skitarii Ranger", 8)

    val u1 = Unit("Skitarii Rangers")
    u1.miniatures.add(m1)
    u1.miniatures.add(m2)
    u1.miniatures.add(m3)
    u1.miniatures.add(m4)
    u1.miniatures.add(m5)

    for (m in u1.miniatures) {
        m.equippedWeapons.add(Weapon("Galvanic Rifle", 0))
    }

    armyState.units.add(u1)

    // A character
    val c1 = Miniature("Tech-Priest Enginseer", 35)
    c1.equippedWeapons.add(Weapon("Laspistol", 0))
    c1.equippedWeapons.add(Weapon("Omnissian axe", 0))
    c1.equippedWeapons.add(Weapon("Servo-arm", 0))

    armyState.characters.add(c1)

    // Name
    armyState.name = "Admech army"

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ArmyLayout(army = armyState)
    }
}

@Composable
fun ArmyLayout(army: Army) {
    Column {
        Text(
            text = army.name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Characters",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        for (character in army.characters) {
            MiniatureLayout(miniature = character, 1, character.points)
        }

        for (unit in army.units) {
            UnitLayout(unit = unit)
        }
    }
}

@Composable
fun UnitLayout(unit: Unit) {
    Column {
        Text(
            text = unit.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Text("No.")

            Spacer(Modifier.width(30.dp))

            Text("Name")

            Spacer(Modifier.width(30.dp))
            
            Text(text = "Weapons")

            Spacer(Modifier.width(30.dp))
            
            Text(text = "Points")
        }

        val groups = unit.miniatures.groupBy { Pair(it.name, it.equippedWeapons) }

        for (group in groups.values) {
            MiniatureLayout(
                miniature = group.first(),
                amount = group.size,
                points = group.sumOf { it.points }
            )
        }
    }
}

@Composable
fun MiniatureLayout(miniature: Miniature, amount: Int, points: Int) {
    Row {
        Text(
            text = amount.toString(),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.width(30.dp))

        Text(
            text = miniature.name,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.width(30.dp))

        Column {
            for (weapon in miniature.equippedWeapons) {
                Text(
                    text = weapon.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Spacer(Modifier.width(30.dp))

        Text(
            text = points.toString(),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

