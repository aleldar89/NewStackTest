package com.example.core

import io.realm.RealmObject

interface FeedItem {
    val id: String
    val url: String
    fun toRealmObject(item: FeedItem): RealmObject
}