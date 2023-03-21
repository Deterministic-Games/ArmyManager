package com.silop.armymanager.data.database

import androidx.room.*
import com.silop.armymanager.data.models.Miniature
import kotlinx.coroutines.flow.Flow

@Dao
interface MiniDao
{
    @Query("SELECT * FROM minis where minis.army = :armyName")
    fun getArmy(armyName: String): Flow<List<Miniature>>

    @Insert
    suspend fun insertMini(mini: Miniature)

    @Delete
    suspend fun deleteMini(mini: Miniature)

    @Update
    suspend fun updateMini(mini: Miniature)
}