package com.app.coindesk.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coins")
data class Coins(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name = "disclaimer")
    @SerializedName("disclaimer")
    val disclaimer: String,

    @ColumnInfo(name = "time")
    @SerializedName("time")
    val time: Time,

    @ColumnInfo(name = "chartName")
    @SerializedName("chartName")
    val chartName: String,

    @ColumnInfo(name = "bpi")
    @SerializedName("bpi")
    val bpi: Bpi
)