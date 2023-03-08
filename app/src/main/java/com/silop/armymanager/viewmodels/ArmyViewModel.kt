package com.silop.armymanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silop.armymanager.dao
import com.silop.armymanager.database.ArmyDatabase
import com.silop.armymanager.models.Miniature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArmyViewModel : ViewModel() {
    private val _minis = MutableStateFlow<List<Miniature>>(emptyList())

    val minis = _minis.asStateFlow()

    fun loadMinis() {
        viewModelScope.launch {
            _minis.value = dao.getMinis()
        }
    }

    fun addMini(mini: Miniature) {
        viewModelScope.launch {
            dao.insertMini(mini)
        }
    }

    fun updateMini(mini: Miniature) {
        viewModelScope.launch {
            dao.updateMini(mini)
        }
    }

    fun removeMini(mini: Miniature) {
        viewModelScope.launch {
            dao.deleteMini(mini)
        }
    }
}