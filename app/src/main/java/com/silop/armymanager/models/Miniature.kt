package com.silop.armymanager.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "minis")
data class Miniature(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "base_points")
    var basePoints: Int = 0,
    @ColumnInfo(name = "equipped_weapons")
    val equippedWeapons: MutableList<Weapon> = mutableListOf(),
    @ColumnInfo(name = "unit")
    var unitName: String
) {
    @get:Ignore
    val points: Int
        get() = equippedWeapons.sumOf { it.points } + basePoints
}