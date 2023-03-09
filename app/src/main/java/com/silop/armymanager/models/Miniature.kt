package com.silop.armymanager.models

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "minis",
    foreignKeys = [
        ForeignKey(
            entity = Army::class,
            parentColumns = ["name"],
            childColumns = ["army"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
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
    var unitName: String,
    @ColumnInfo(name = "army")
    var armyName: String
) {
    @get:Ignore
    val points: Int
        get() = equippedWeapons.sumOf { it.points } + basePoints
}