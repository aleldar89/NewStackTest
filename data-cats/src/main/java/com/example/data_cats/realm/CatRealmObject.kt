package com.example.data_cats.realm

import com.example.core.RealmItem
import com.example.data_cats.dto.CatImage
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CatRealmObject(
    @PrimaryKey
    override var id: String = "id",
    var url: String = "url",
    var width: Int = 0,
    var height: Int = 0,
) : RealmObject(), RealmItem<CatImage> {

    override fun toDto() = CatImage(id, url, width, height)
}