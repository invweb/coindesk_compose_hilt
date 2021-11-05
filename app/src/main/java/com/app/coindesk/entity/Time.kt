package com.app.coindesk.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Time (
    @ColumnInfo(name = "updated")
    @SerializedName("updated")
    val updated: String,

    @ColumnInfo(name = "updatedISO")
    @SerializedName("updatedISO")
    val updatedISO: String,

    @ColumnInfo(name = "updateduk")
    @SerializedName("updateduk")
    val updateduk: String
)