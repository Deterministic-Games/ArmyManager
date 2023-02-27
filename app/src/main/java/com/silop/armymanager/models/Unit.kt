package com.silop.armymanager.models

data class Unit(
    val name: String,
    val miniatures: MutableList<Miniature> = mutableListOf()
)