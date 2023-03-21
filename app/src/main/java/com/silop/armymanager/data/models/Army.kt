package com.silop.armymanager.data.models

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
    var id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String
)