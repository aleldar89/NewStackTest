package com.example.data_cats.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface CatRepositoryModule {

    @Singleton
    @Binds
    fun bindsCatRepository(impl: CatRepositoryImpl): CatRepository
}