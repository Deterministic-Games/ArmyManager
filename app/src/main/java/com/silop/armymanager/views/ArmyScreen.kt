package com.silop.armymanager.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.silop.armymanager.viewmodels.ArmyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.silop.armymanager.models.Miniature
import com.silop.armymanager.ui.theme.ArmyManagerTheme
import com.silop.armymanager.R
import kotlinx.coroutines.launch

@Composable
fun ArmyScreen(
    armyViewModel: ArmyViewModel = viewModel(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
) {
    val minis by armyViewModel.minis.collectAsState(emptyList())

    // Name
    val name = "Admech Army"

    ArmyManagerTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet(
                        modifier = Modifier
                            .width(175.dp)
                            .fillMaxHeight()
                    ) {
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = "Idk"
                                )
                            },
                            selected = false,
                            onClick = {
                                /*TODO*/
                            }
                        )
                    }
                }
            ) {
                Scaffold(topBar = {
                    TopBar(name, drawerState)
                }, content = { innerPadding ->
                    ArmyLayout(minis, innerPadding)
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(armyName: String, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()

    CenterAlignedTopAppBar(title = {
        Text(
            text = armyName, maxLines = 1
        )
    }, navigationIcon = {
        IconButton(onClick = {
            scope.launch {
                drawerState.open()
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = null
            )
        }
    }, actions = {
        IconButton(onClick = {
            /* TODO: open settings */
        }) {
            Icon(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = null
            )
        }
    })
}

@Composable
fun ArmyLayout(minis: List<Miniature>, padding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = padding
    ) {
        minis.groupBy { it.unitName }.forEach { (unitName, miniatures) ->
            item {
                UnitLayout(unitName = unitName, minis = miniatures)
            }
        }
    }
}

@Composable
fun UnitLayout(unitName: String, minis: List<Miniature>) {
    Column(
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.small)
            .padding(top = 2.dp, bottom = 2.dp, start = 5.dp, end = 5.dp)
            .clickable {

            },
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = unitName,
            style = MaterialTheme.typography.titleMedium
        )

        Row(Modifier.padding(start = 5.dp, end = 5.dp)) {
            Text(
                modifier = Modifier.width(30.dp), text = "No."
            )

            Spacer(Modifier.width(10.dp))

            Text(
                modifier = Modifier.width(110.dp), text = "Name"
            )

            Spacer(Modifier.width(10.dp))

            Text(
                modifier = Modifier.width(110.dp), text = "Weapons"
            )

            Spacer(Modifier.width(10.dp))

            Text(
                modifier = Modifier.width(50.dp), text = "Pts."
            )
        }

        val groups = minis.groupBy { Pair(it.name, it.equippedWeapons) }

        Column(
            Modifier.padding(start = 5.dp, end = 5.dp)
        ) {
            for (group in groups.values) {
                MiniatureLayout(
                    mini = group.first(),
                    amount = group.size,
                    points = group.sumOf { it.points })
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
fun MiniatureLayout(mini: Miniature, amount: Int, points: Int) {
    Row(
        Modifier
            .padding(start = 5.dp, end = 5.dp)
            .clickable {

            }) {
        Text(
            modifier = Modifier.width(30.dp),
            text = amount.toString(),
            style = MaterialTheme.typography.titleSmall,
        )

        Spacer(Modifier.width(10.dp))

        Text(
            modifier = Modifier.width(110.dp),
            text = mini.name,
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(Modifier.width(10.dp))


        Column(Modifier.width(110.dp)) {
            for (weapon in mini.equippedWeapons) {
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
