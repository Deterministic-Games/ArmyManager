package com.silop.armymanager.viewmodels

import androidx.lifecycle.ViewModel
import com.silop.armymanager.models.Army
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArmyViewModel : ViewModel() {
    private val _armyState = MutableStateFlow(Army(""))
    val armyState = _armyState.asStateFlow()
}