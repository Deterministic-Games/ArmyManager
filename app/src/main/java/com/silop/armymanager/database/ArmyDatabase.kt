package com.silop.armymanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.silop.armymanager.models.Miniature

@Database(entities = [Miniature::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class ArmyDatabase : RoomDatabase() {
    abstract fun armyDao(): ArmyDao

    companion object {
        @Volatile
        private var INSTANCE: ArmyDatabase? = null

        fun getDatabase(context: Context): ArmyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = ArmyDatabase::class.java,
                    name = "army_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}