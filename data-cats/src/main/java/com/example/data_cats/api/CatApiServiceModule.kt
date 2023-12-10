package com.example.data_cats.api

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface CatApiServiceModule {

    @Singleton
    @Binds
    fun bindsCameraApiService(impl: CatApiServiceImpl): CatApiService
}