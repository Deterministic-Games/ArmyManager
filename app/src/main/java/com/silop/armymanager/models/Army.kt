package com.silop.armymanager.models

data class Army(
    var name: String,
    val units: MutableList<Unit> = mutableListOf(),
    val characters: Unit = Unit("Characters")
)