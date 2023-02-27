package com.silop.armymanager.models

data class Unit(
    var name: String,
    //var points: Int = 0,
    val miniatures: MutableList<Miniature> = mutableListOf()
) {
    val points: Int
        get() = miniatures.sumOf { it.basePoints }
}