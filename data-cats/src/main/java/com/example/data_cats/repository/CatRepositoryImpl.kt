package com.example.data_cats.repository

import com.example.core.DatabaseOperations
import com.example.data_cats.api.CatApiService
import com.example.data_cats.dto.CatImage
import com.example.data_cats.realm.CatRealmModule
import com.example.data_cats.realm.CatRealmObject
import io.realm.RealmConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val apiService: CatApiService
) : CatRepository {

    private val realmVersion = 1L
    private val realmFileName = "catsDb"

    private val realmConfig = RealmConfiguration.Builder()
        .name(realmFileName)
        .schemaVersion(realmVersion)
        .modules(CatRealmModule())
        .build()

    private val databaseOperations: DatabaseOperations = DatabaseOperations(realmConfig)

    override suspend fun getLocalData(): Flow<List<CatImage>> = flow {
        val data = databaseOperations.doLocalRequest<CatRealmObject, CatImage>()
        emit(data)
    }.flowOn(Dispatchers.IO)

    override suspend fun getRemoteData() =
        databaseOperations.insertRemoteDataToDatabase<CatRealmObject, CatImage>(
            apiService.getRandomImages()
        )
}