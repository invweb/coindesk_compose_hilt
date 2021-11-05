package com.app.coindesk.database

import androidx.room.TypeConverter
import com.app.coindesk.entity.Bpi
import com.app.coindesk.entity.BpiValues
import com.app.coindesk.entity.Time
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromJsonToBpi(bpiJson: String): Bpi {
        return Gson().fromJson(bpiJson, Bpi::class.java)
    }

    @TypeConverter
    fun fromBpiToJson(bpi: Bpi): String? {
        return Gson().toJson(bpi)
    }

    @TypeConverter
    fun fromJsonToTime(timeJson: String): Time {
        return Gson().fromJson(timeJson, Time::class.java)
    }

    @TypeConverter
    fun fromTimeToJson(time: Time): String? {
        return Gson().toJson(time)
    }

    @TypeConverter
    fun fromBpiListToJson(arrayBPI: ArrayList<BpiValues>): String? {
        return Gson().toJson(arrayBPI)
    }

    @TypeConverter
    fun fromJsonToBpList(bpiJson: String): ArrayList<BpiValues> {
        val listType = object : TypeToken<ArrayList<BpiValues>>() {}.type
        return Gson().fromJson(bpiJson, listType)
        //return Gson().fromJson(bpiJson, object : TypeToken<ArrayList<BpiValues>>() {}.type)
    }
}