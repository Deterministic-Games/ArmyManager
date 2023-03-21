package com.silop.armymanager.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.DialogProperties
import com.silop.armymanager.R

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