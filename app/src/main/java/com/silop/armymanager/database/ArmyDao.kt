package com.silop.armymanager.database

import androidx.room.*
import com.silop.armymanager.models.Army

@Dao
interface ArmyDao {
    @Query("SELECT * FROM armies")
    suspend fun getArmies(): List<Army>

    @Query("SELECT * FROM armies WHERE name = :armyName")
    suspend fun getArmy(armyName: String): Army

    @Insert
    suspend fun insertArmy(army: Army)

    @Delete
    suspend fun deleteArmy(army: Army)

    @Update
    suspend fun updateArmy(army: Army)
}
