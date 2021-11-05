package com.app.coindesk.application

import android.app.Application
import com.app.coindesk.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeskApplication: Application() {

    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        database = AppDatabase.getInstance(this)!!
    }
}