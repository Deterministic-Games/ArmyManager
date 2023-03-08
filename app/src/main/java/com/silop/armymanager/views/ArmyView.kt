package com.silop.armymanager.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.silop.armymanager.viewmodels.ArmyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.silop.armymanager.models.Miniature

@Composable
fun ArmyScreen(
    armyViewModel: ArmyViewModel = viewModel()
) {
    val minis by armyViewModel.minis.collectAsState(emptyList())

    // Name
    val name = "Admech army"

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ArmyLayout(name = name, minis)
    }
}

@Composable
fun ArmyLayout(name: String, minis: List<Miniature>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge
        )

        minis.groupBy { it.unitName }.forEach { (unitName, miniatures) ->
            UnitLayout(name = unitName, minis = miniatures)
        }
    }
}

@Composable
fun UnitLayout(name: String, minis: List<Miniature>) {
    Column(
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.small)
            .padding(top = 2.dp, bottom = 2.dp)
            .background(color = MaterialTheme.colorScheme.surface),
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = name,
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier.padding(start = 5.dp, end = 5.dp)
        ) {
            Text(
                modifier = Modifier.width(30.dp),
                text = "No."
            )

            Spacer(Modifier.width(15.dp))

            Text(
                modifier = Modifier.width(100.dp),
                text = "Name"
            )

            Spacer(Modifier.width(15.dp))
            
            Text(
                modifier = Modifier.width(100.dp),
                text = "Weapons"
            )

            Spacer(Modifier.width(15.dp))
            
            Text(
                modifier = Modifier.width(50.dp),
                text = "Points"
            )
        }

        val groups = minis.groupBy { Pair(it.name, it.equippedWeapons) }

        for (group in groups.values) {
            MiniatureLayout(
                miniature = group.first(),
                amount = group.size,
                points = group.sumOf { it.points }
            )
        }
        Spacer(Modifier.height(5.dp))
    }
}

@Composable
fun MiniatureLayout(miniature: Miniature, amount: Int, points: Int) {
    Row(
        modifier = Modifier.padding(start = 5.dp, end = 5.dp)
    ) {
        Text(
            modifier = Modifier.width(30.dp),
            text = amount.toString(),
            style = MaterialTheme.typography.titleSmall,
        )

        Spacer(Modifier.width(15.dp))

        Text(
            modifier = Modifier.width(100.dp),
            text = miniature.name,
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(Modifier.width(15.dp))

        Column {
            for (weapon in miniature.equippedWeapons) {
                Text(
                    modifier = Modifier.width(100.dp),
                    text = weapon.name,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }

        Spacer(Modifier.width(15.dp))

        Text(
            modifier = Modifier.width(50.dp),
            text = points.toString(),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

