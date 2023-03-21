package com.silop.armymanager.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.silop.armymanager.viewmodels.ArmyViewModel
import com.silop.armymanager.data.models.Miniature
import com.silop.armymanager.R
import com.silop.armymanager.ui.screens.components.UnitDialog
import kotlinx.coroutines.launch

@Composable
fun ArmyScreen(
    armyViewModel: ArmyViewModel = viewModel(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
) {
    val minis by armyViewModel.minis.collectAsState(emptyList())
    val armies by armyViewModel.armies.collectAsState(emptyList())
    val army by armyViewModel.army.collectAsState()

    val openDialog = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier
                        .width(192.dp)
                        .fillMaxHeight()
                ) {
                    armies.forEach {
                        NavigationDrawerItem(
                            label = { Text(text = it.name) },
                            selected = it.name == army.name,
                            onClick = {
                                if (it.name != army.name) {
                                    armyViewModel.loadArmy(it.name)
                                }
                            }
                        )
                    }
                }
            }
        ) {
            Scaffold(
                topBar = { TopBar(army.name, drawerState) },
                bottomBar = { BottomBar(armyViewModel) },
                content = { padding -> ArmyLayout(minis, padding) },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { openDialog.value = true }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.add),
                            contentDescription = "Add unit"
                        )
                    }
                }
            )
            AnimatedVisibility(
                visible = openDialog.value,
                enter = expandIn(),
                exit = shrinkOut()
            ) {
                UnitDialog(openDialog, "")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(armyName: String, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = armyName, maxLines = 1
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.menu),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    /* TODO: open settings */
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.edit),
                    contentDescription = null
                )
            }
        })
}

@Composable
fun BottomBar(viewModel: ArmyViewModel) {
    BottomAppBar {
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Menu, contentDescription = "Localized description")
        }
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Menu, contentDescription = "Localized description")
        }
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Menu, contentDescription = "Localized description")
        }
    }
}

@Composable
fun ArmyLayout(minis: List<Miniature>, padding: PaddingValues) {
    val padding2 = PaddingValues(
        start = 16.dp,
        end = 16.dp,
        top = padding.calculateTopPadding(),
        bottom = padding.calculateBottomPadding()
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = padding2
    ) {
        minis.groupBy { it.unitName }.forEach { (unitName, miniatures) ->
            item {
                UnitLayout(unitName, miniatures)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitLayout(unitName: String, minis: List<Miniature>) {
    ElevatedCard(
        onClick = {
            /*TODO*/
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = unitName,
                style = MaterialTheme.typography.headlineSmall
            )

            Divider(Modifier.padding(start = 16.dp, end = 16.dp))

            minis.groupBy { it.name }.forEach { (name, minis) ->
                ListItem(
                    headlineText = { Text(name) },
                    supportingText = { Text("${minis.sumOf { it.points }} pts") },
                    leadingContent = { Text(minis.size.toString()) },
                    trailingContent = {
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.arrow_right),
                                contentDescription = null
                            )
                        }
                    }
                )
            }

            Divider(Modifier.padding(start = 16.dp, end = 16.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Points total: ${minis.sumOf { it.points }}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun MiniatureLayout(name: String, minis: List<Miniature>) {

}