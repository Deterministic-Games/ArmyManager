package com.silop.armymanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silop.armymanager.data.database.ArmyDao
import com.silop.armymanager.data.database.MiniDao
import com.silop.armymanager.data.models.Army
import com.silop.armymanager.data.models.Miniature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArmyViewModel @Inject constructor(
    private val miniDao: MiniDao,
    private val armyDao: ArmyDao
) : ViewModel() {
    private val _armies = MutableStateFlow<List<Army>>(emptyList())
    val armies = _armies.asStateFlow()

    private val _army = MutableStateFlow(Army(name = ""))
    val army = _army.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _minis = _army
        .flatMapLatest { army -> miniDao.getArmy(army.name) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val minis = _minis


    fun loadArmies() {
        viewModelScope.launch {
            armyDao.getArmies().collectLatest {
                _armies.value = it
            }
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
        }
    }

    fun loadArmy(armyName: String) {
        viewModelScope.launch {
            armyDao.getArmy(armyName).collectLatest {
                _army.value = it
            }
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

    fun deleteMini(mini: Miniature) {
        viewModelScope.launch {
            miniDao.deleteMini(mini)
        }
    }
}