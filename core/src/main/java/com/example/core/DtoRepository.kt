package com.example.core

import kotlinx.coroutines.flow.Flow

interface DtoRepository<T> {

    suspend fun getLocalData(): Flow<List<T>>
    suspend fun getRemoteData()
}