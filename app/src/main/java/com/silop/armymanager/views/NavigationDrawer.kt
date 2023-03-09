package com.silop.armymanager.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(onArmySelected: (String) -> Unit) {
    Column {
        Card(onClick = {
            onArmySelected("Army 1")
        }) {
            Text("Army 1")
        }
        Card(onClick = {
            onArmySelected("Army 2")
        }) {
            Text("Army 2")
        }
        Card(onClick = {
            onArmySelected("Army 3")
        }) {
            Text("Army 3")
        }
    }
}