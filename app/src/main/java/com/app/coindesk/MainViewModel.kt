package com.app.coindesk

import android.app.Activity
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.coindesk.database.AppDatabase
import com.app.coindesk.entity.Coins
import com.app.coindesk.placeholder.JsonPlaceHolderApi
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.app.coindesk.application.DeskApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val jsonPlaceHolderApi: JsonPlaceHolderApi) :
    ViewModel(), Callback<Coins> {
    private lateinit var app: Application

    fun saveCoinsToDB(
        activity: Activity
    ){
        jsonPlaceHolderApi.getCoins().enqueue(this)
        app = activity.application
    }

    override fun onResponse(call: Call<Coins>, response: Response<Coins>) {
        val body : Coins? = response.body()
        val bodyNotNull : Coins = body!!
        saveDataToDbAsync(bodyNotNull, app)
    }

    override fun onFailure(call: Call<Coins>, t: Throwable) {
        Timber.d("MainViewModel Callback<Coins> onFailure")
    }

    private fun saveDataToDbAsync(coins: Coins, app: Application) {
        viewModelScope.launch(Dispatchers.IO) {
            run {
                AppDatabase.getInstance(app)!!.getDao().insertCoins(coins)
            }
        }
    }

    fun observeDataIdDB(deskApplication: DeskApplication): LiveData<List<Coins>> {
        return deskApplication.database.getDao().getCoins()
    }
}