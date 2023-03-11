package com.silop.armymanager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ArmyManagerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}