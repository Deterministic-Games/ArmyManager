package com.silop.armymanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.silop.armymanager.data.models.Army
import com.silop.armymanager.data.models.Miniature

@Database(entities = [Miniature::class, Army::class], version = 2, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class ArmyDatabase : RoomDatabase() {
    abstract val miniDao: MiniDao
    abstract val armyDao: ArmyDao
}