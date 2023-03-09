package com.silop.armymanager.models

import androidx.room.*

@Entity(
    tableName = "armies",
    indices = [
        Index(
            value = ["name"],
            unique = true
        )
    ]
)
data class Army(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "name")
    val name: String
)