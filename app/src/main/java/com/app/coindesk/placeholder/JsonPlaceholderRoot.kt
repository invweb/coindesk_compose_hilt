package com.app.coindesk.placeholder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JsonPlaceholderRoot {
    private const val BASE_URL = "https://api.coindesk.com/"

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JsonPlaceHolderApi::class.java)
}
