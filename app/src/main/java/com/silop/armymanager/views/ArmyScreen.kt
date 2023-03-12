package com.silop.armymanager.views

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.silop.armymanager.viewmodels.ArmyViewModel
import com.silop.armymanager.models.Miniature
import com.silop.armymanager.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
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

@Composable
fun UnitDialog(openDialog: MutableState<Boolean>, unitName: String = "") {
    val name = remember { mutableStateOf(unitName) }

    AlertDialog(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = { openDialog.value = false },
        title = { 
            Text(
                text = "Unit Editor"
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    singleLine = true,
                    placeholder = { Text("Unit Name") },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.edit),
                            contentDescription = "Edit unit name"
                        )
                    }
                )
            }
        },
        confirmButton = {
            ElevatedButton(
                onClick = { openDialog.value = false }
            ) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            IconButton(
                onClick = { openDialog.value = false }
            ) {
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = "Exit editor"
                )
            }
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
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
                    painter = painterResource(R.drawable.settings),
                    contentDescription = null
                )
            }
        })
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