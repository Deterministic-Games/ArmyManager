package com.silop.armymanager.models

data class Miniature(
    val name: String,
    var basePoints: Int = 0,
    val equippedWeapons: MutableList<Weapon> = mutableListOf()
) {
    val points: Int
        get() = equippedWeapons.sumOf { it.points } + basePoints
}