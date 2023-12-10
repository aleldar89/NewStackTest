package com.example.core

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers

class DatabaseOperations(
    val realmConfiguration: RealmConfiguration,
) {

    suspend inline fun <reified E, D> doLocalRequest(
    ): List<D> where E : RealmObject, E : RealmItem<D>, D : FeedItem {
        val items = mutableListOf<D>()
        val realm = Realm.getInstance(realmConfiguration)
        try {
            realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
                items.addAll(realmTransaction.where(E::class.java)
                    .findAll()
                    .map { realmObject ->
                        realmObject.toDto()
                    }
                )
            }
        } catch (e: Exception) {
            throw e
        } finally {
            realm.close()
        }
        return items
    }

    suspend inline fun <reified E, D> insertRemoteDataToDatabase(
        list: List<FeedItem>
    ) where E : RealmObject, E : RealmItem<D>, D : FeedItem {
        val realm = Realm.getInstance(realmConfiguration)
        try {
            val realmObjectsList = list.map {
                it.toRealmObject(it)
            }
            realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
                realmTransaction.insertOrUpdate(realmObjectsList)
            }
        } catch (e: Exception) {
            throw e
        } finally {
            realm.close()
        }
    }
}