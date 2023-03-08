package com.silop.armymanager.database

import androidx.room.*
import com.silop.armymanager.models.Miniature

@Dao
interface ArmyDao
{
    @Query("SELECT * FROM minis")
    suspend fun getMinis(): List<Miniature>

    @Insert
    suspend fun insertMini(mini: Miniature)

    @Delete
    suspend fun deleteMini(mini: Miniature)

    @Update
    suspend fun updateMini(mini: Miniature)
}