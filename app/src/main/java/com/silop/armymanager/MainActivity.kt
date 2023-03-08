package com.silop.armymanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import com.silop.armymanager.database.ArmyDao
import com.silop.armymanager.database.ArmyDatabase
import com.silop.armymanager.models.Miniature
import com.silop.armymanager.ui.theme.ArmyManagerTheme
import com.silop.armymanager.viewmodels.ArmyViewModel
import com.silop.armymanager.views.Navigation

lateinit var dao: ArmyDao

class MainActivity : ComponentActivity() {
    private val armyViewModel: ArmyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = ArmyDatabase.getDatabase(this)
        dao = db.armyDao()

        setContent {
            LaunchedEffect(Unit) {
                try {
                    armyViewModel.loadMinis()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            Navigation(viewModel = armyViewModel)
        }
    }
}