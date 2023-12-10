package com.example.data_dogs.api

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DogApiServiceModule {

    @Singleton
    @Binds
    fun bindsDoorApiService(impl: DogApiServiceImpl): DogApiService
}