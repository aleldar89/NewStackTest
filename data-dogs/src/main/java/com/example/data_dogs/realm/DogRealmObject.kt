package com.example.data_dogs.realm

import com.example.core.RealmItem
import com.example.data_dogs.dto.DogImage
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DogRealmObject(
    @PrimaryKey
    override var id: String = "id",
    var url: String = "url",
    var width: Int = 0,
    var height: Int = 0
) : RealmObject(), RealmItem<DogImage> {

    override fun toDto() = DogImage(id, url, width, height)
}