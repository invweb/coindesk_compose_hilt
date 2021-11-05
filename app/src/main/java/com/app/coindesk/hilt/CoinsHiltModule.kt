package com.app.coindesk.hilt

import com.app.coindesk.placeholder.JsonPlaceHolderApi
import com.app.coindesk.placeholder.JsonPlaceholderRoot
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class CoinsHiltModule {
    @Provides
    fun provideApiService(): JsonPlaceHolderApi = JsonPlaceholderRoot.api
}