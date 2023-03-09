package com.silop.armymanager.database

import androidx.room.*
import com.silop.armymanager.models.Miniature

@Dao
interface MiniDao
{
    @Query("SELECT * FROM minis")
    suspend fun getMinis(): List<Miniature>

    @Query("SELECT * FROM minis where minis.army = :armyName")
    suspend fun getArmy(armyName: String): List<Miniature>

    @Insert
    suspend fun insertMini(mini: Miniature)

    @Delete
    suspend fun deleteMini(mini: Miniature)

    @Update
    suspend fun updateMini(mini: Miniature)
}