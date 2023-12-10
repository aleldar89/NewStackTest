package com.example.data_dogs.repository

import com.example.core.DatabaseOperations
import com.example.data_dogs.api.DogApiService
import com.example.data_dogs.dto.DogImage
import com.example.data_dogs.realm.DogRealmModule
import com.example.data_dogs.realm.DogRealmObject
import io.realm.RealmConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val apiService: DogApiService
) : DogRepository {

    private val realmVersion = 1L
    private val realmFileName = "dogsDb"

    private val realmConfig = RealmConfiguration.Builder()
        .name(realmFileName)
        .schemaVersion(realmVersion)
        .modules(DogRealmModule())
        .build()

    private val databaseOperations: DatabaseOperations = DatabaseOperations(realmConfig)

    override suspend fun getLocalData(): Flow<List<DogImage>> = flow {
        val data = databaseOperations.doLocalRequest<DogRealmObject, DogImage>()
        emit(data)
    }.flowOn(Dispatchers.IO)

    override suspend fun getRemoteData() {
        databaseOperations.insertRemoteDataToDatabase<DogRealmObject, DogImage>(
            apiService.getRandomImages()
        )
    }
}