package com.app.coindesk.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Bpi(
    @ColumnInfo(name = "USD")
    @SerializedName("USD")
    val usd: BpiValues,

    @ColumnInfo(name = "GBP")
    @SerializedName("GBP")
    val gbp: BpiValues,

    @ColumnInfo(name = "EUR")
    @SerializedName("EUR")
    val eur: BpiValues
)