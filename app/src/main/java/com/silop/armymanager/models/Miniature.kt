package com.silop.armymanager.models

data class Miniature(
    val name: String,
    val equippedWeapons: MutableList<Weapon> = mutableListOf()
)