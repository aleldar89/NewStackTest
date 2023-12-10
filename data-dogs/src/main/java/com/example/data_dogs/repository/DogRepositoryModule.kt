package com.example.data_dogs.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DogRepositoryModule {

    @Singleton
    @Binds
    fun bindsDogRepository(impl: DogRepositoryImpl): DogRepository
}