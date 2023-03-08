package com.silop.armymanager.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.silop.armymanager.viewmodels.ArmyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.silop.armymanager.models.Miniature
import com.silop.armymanager.ui.theme.ArmyManagerTheme
import com.silop.armymanager.R

@Composable
fun ArmyScreen(
    armyViewModel: ArmyViewModel = viewModel(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
) {
    val minis by armyViewModel.minis.collectAsState(emptyList())

    // Name
    val name = "Admech army"

    ArmyManagerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ArmyLayout(name = name, minis)

            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    DrawerContent(onArmySelected = {
                        /* TODO */
                    })
                }
            ) {

            }
        }
    }
}

@Composable
fun ArmyLayout(name: String, minis: List<Miniature>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.small
            )
            .clickable {

            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

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
            .padding(top = 2.dp, bottom = 2.dp, start = 5.dp, end = 5.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.small
            )
            .clickable {

            },
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Row(Modifier.padding(start = 5.dp, end = 5.dp)) {
            Text(
                modifier = Modifier.width(30.dp),
                text = "No.",
                color = MaterialTheme.colorScheme.tertiary
            )

            Spacer(Modifier.width(10.dp))

            Text(
                modifier = Modifier.width(110.dp),
                text = "Name",
                color = MaterialTheme.colorScheme.tertiary
            )

            Spacer(Modifier.width(10.dp))
            
            Text(
                modifier = Modifier.width(110.dp),
                text = "Weapons",
                color = MaterialTheme.colorScheme.tertiary
            )

            Spacer(Modifier.width(10.dp))
            
            Text(
                modifier = Modifier.width(50.dp),
                text = "Pts.",
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        val groups = minis.groupBy { Pair(it.name, it.equippedWeapons) }

        Column(
            Modifier
                .padding(start = 5.dp, end = 5.dp)
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = MaterialTheme.shapes.extraSmall
                )
        ) {
            for (group in groups.values) {
                MiniatureLayout(
                    miniature = group.first(),
                    amount = group.size,
                    points = group.sumOf { it.points }
                )
            }
        }
        Row(
            modifier = Modifier.padding(start = 5.dp, end = 5.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = "Points total")

            Spacer(Modifier.width(30.dp))

            Text(text = minis.sumOf { it.points }.toString())
        }

        Spacer(Modifier.height(5.dp))
    }
}

@Composable
fun MiniatureLayout(miniature: Miniature, amount: Int, points: Int) {
    Row(
        Modifier
            .padding(start = 5.dp, end = 5.dp)
            .clickable {

            }
    ) {
        Text(
            modifier = Modifier.width(30.dp),
            text = amount.toString(),
            style = MaterialTheme.typography.titleSmall,
        )

        Spacer(Modifier.width(10.dp))

        Text(
            modifier = Modifier.width(110.dp),
            text = miniature.name,
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(Modifier.width(10.dp))


        Column(Modifier.width(110.dp)) {
            for (weapon in miniature.equippedWeapons) {
                Text(
                    modifier = Modifier.width(110.dp),
                    text = weapon.name,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }

        Spacer(Modifier.width(10.dp))

        Text(
            modifier = Modifier.width(50.dp),
            text = points.toString(),
            style = MaterialTheme.typography.titleSmall
        )
    }
}
