package com.silop.armymanager.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.silop.armymanager.models.Miniature
import com.silop.armymanager.models.Weapon

class DataConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromWeaponList(weapons: MutableList<Weapon>?): String? {
        if (weapons == null) {
            return null
        }
        val type = object : TypeToken<MutableList<Weapon>>() {}.type

        return gson.toJson(weapons, type)
    }

    @TypeConverter
    fun toWeaponList(weaponsJson: String?): MutableList<Weapon>? {
        if (weaponsJson == null) {
            return null
        }
        val type = object : TypeToken<MutableList<Weapon>>() {}.type

        return gson.fromJson(weaponsJson, type)
    }
}