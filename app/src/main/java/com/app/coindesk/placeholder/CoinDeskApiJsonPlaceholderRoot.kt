package com.app.coindesk.placeholder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoinDeskApiJsonPlaceholderRoot {
    private const val BASE_URL = "https://api.coindesk.com/"

    val api: JsonPlaceHolderApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JsonPlaceHolderApi::class.java)
}
