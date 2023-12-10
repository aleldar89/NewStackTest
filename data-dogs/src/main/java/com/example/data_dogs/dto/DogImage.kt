package com.example.data_dogs.dto

import com.example.core.FeedItem
import com.example.data_dogs.realm.DogRealmObject
import io.realm.RealmObject

data class DogImage(
    override val id: String,
    override val url: String,
    val width: Int,
    val height: Int,
) : FeedItem {

    override fun toRealmObject(item: FeedItem): RealmObject =
        DogRealmObject(id, url, width, height)
}