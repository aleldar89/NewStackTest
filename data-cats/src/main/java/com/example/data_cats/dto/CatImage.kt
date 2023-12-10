package com.example.data_cats.dto

import com.example.core.FeedItem
import com.example.data_cats.realm.CatRealmObject
import io.realm.RealmObject

data class CatImage (
    override val id: String,
    override val url: String,
    val width: Int,
    val height: Int
) : FeedItem {

    override fun toRealmObject(item: FeedItem): RealmObject =
        CatRealmObject(id, url, width, height)
}

