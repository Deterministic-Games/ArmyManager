package com.silop.armymanager.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silop.armymanager.database.ArmyDao
import com.silop.armymanager.database.MiniDao
import com.silop.armymanager.models.Army
import com.silop.armymanager.models.Miniature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArmyViewModel @Inject constructor(
    private val miniDao: MiniDao,
    private val armyDao: ArmyDao
) : ViewModel() {
    private val _minis = MutableStateFlow<List<Miniature>>(emptyList())

    val minis = _minis.asStateFlow()

    private val _armies = MutableStateFlow<List<Army>>(emptyList())

    val armies = _armies.asStateFlow()

    private val _army = MutableStateFlow(Army(name = ""))
    val army = _army.asStateFlow()

    fun loadArmies() {
        viewModelScope.launch {
            _armies.value = armyDao.getArmies()
        }
    }

    fun addArmy(army: Army) {
        viewModelScope.launch {
            armyDao.insertArmy(army)
        }
    }

    fun deleteArmy(army: Army) {
        viewModelScope.launch {
            armyDao.deleteArmy(army)
            _minis.value = emptyList()
            _armies.value = armyDao.getArmies()
        }
    }

    fun loadArmy(armyName: String) {
        viewModelScope.launch {
            _minis.value = miniDao.getArmy(armyName)
            _army.value = armyDao.getArmy(armyName)
        }
    }

    fun addMini(mini: Miniature) {
        viewModelScope.launch {
            miniDao.insertMini(mini)
        }
    }

    fun updateMini(mini: Miniature) {
        viewModelScope.launch {
            miniDao.updateMini(mini)
        }
    }

    fun removeMini(mini: Miniature) {
        viewModelScope.launch {
            miniDao.deleteMini(mini)
        }
    }
}