package com.silop.armymanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.silop.armymanager.models.Army
import com.silop.armymanager.models.Miniature

@Database(entities = [Miniature::class, Army::class], version = 2, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class ArmyDatabase : RoomDatabase() {
    //abstract fun miniDao(): MiniDao
    //abstract fun armyDao(): ArmyDao

    abstract val miniDao: MiniDao
    abstract val armyDao: ArmyDao
}