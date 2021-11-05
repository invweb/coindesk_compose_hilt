package com.app.coindesk.placeholder

import com.app.coindesk.entity.Coins
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {
    @GET("/v1/bpi/currentprice.json")
    fun getCoins(): Call<Coins>
}
