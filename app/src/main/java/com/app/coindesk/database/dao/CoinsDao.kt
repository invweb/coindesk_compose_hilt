package com.app.coindesk.database.dao

import androidx.lifecycle.LiveData
import com.app.coindesk.entity.Coins
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CoinsDao {
    @Query("SELECT * FROM coins")
    fun getCoins(): LiveData<List<Coins>>

    @Insert
    fun insertCoins(coinsListList: Coins): Long

    @Query("DELETE FROM coins")
    fun truncateCoins()
}
