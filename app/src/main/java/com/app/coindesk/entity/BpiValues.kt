package com.app.coindesk.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class BpiValues(
    @ColumnInfo(name = "code")
    @SerializedName("code")
    val code: String,

    @ColumnInfo(name = "symbol")
    @SerializedName("symbol")
    val symbol: String,

    @ColumnInfo(name = "rate")
    @SerializedName("rate")
    val rate: String,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,

    @ColumnInfo(name = "rate_float")
    @SerializedName("rate_float")
    val rateFloat: Double
)