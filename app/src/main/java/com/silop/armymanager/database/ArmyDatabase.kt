package com.silop.armymanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.silop.armymanager.models.Army
import com.silop.armymanager.models.Miniature

@Database(entities = [Miniature::class, Army::class], version = 2, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class ArmyDatabase : RoomDatabase() {
    abstract fun miniDao(): MiniDao
    abstract fun armyDao(): ArmyDao
}