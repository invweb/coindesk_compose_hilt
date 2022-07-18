package com.app.coindesk.hilt

import android.content.Context
import com.app.coindesk.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideChannelDao(@ApplicationContext context: Context): AppDatabase {
        lateinit var database: AppDatabase
        AppDatabase.getInstance(context)?.let {
            database = it
        }
        return database
    }
}