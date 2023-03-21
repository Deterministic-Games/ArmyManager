package com.silop.armymanager.data.database

import androidx.room.*
import com.silop.armymanager.data.models.Army
import kotlinx.coroutines.flow.Flow

@Dao
interface ArmyDao {
    @Query("SELECT * FROM armies")
    fun getArmies(): Flow<List<Army>>

    @Query("SELECT * FROM armies WHERE name = :armyName")
    fun getArmy(armyName: String): Flow<Army>

    @Insert
    suspend fun insertArmy(army: Army)

    @Delete
    suspend fun deleteArmy(army: Army)

    @Update
    suspend fun updateArmy(army: Army)
}
