package com.silop.armymanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import com.silop.armymanager.database.ArmyDao
import com.silop.armymanager.database.MiniDao
import com.silop.armymanager.database.ArmyDatabase
import com.silop.armymanager.viewmodels.ArmyViewModel
import com.silop.armymanager.views.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val armyViewModel: ArmyViewModel by viewModels()

    @Inject lateinit var miniDao: MiniDao
    @Inject lateinit var armyDao: ArmyDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LaunchedEffect(Unit) {
                armyViewModel.loadArmies()
            }
            Navigation(viewModel = armyViewModel)
        }
    }
}