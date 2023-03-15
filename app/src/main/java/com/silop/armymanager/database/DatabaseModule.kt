package com.silop.armymanager.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideArmyDao(armyDatabase: ArmyDatabase): ArmyDao {
        return armyDatabase.armyDao
    }
    @Provides
    fun provideMiniDao(armyDatabase: ArmyDatabase): MiniDao {
        return armyDatabase.miniDao
    }
    @Provides
    @Singleton
    fun provideArmyDatabase(@ApplicationContext context: Context): ArmyDatabase {
        return Room.databaseBuilder(
            context,
            ArmyDatabase::class.java,
            "army_database"
        ).build()
    }
}