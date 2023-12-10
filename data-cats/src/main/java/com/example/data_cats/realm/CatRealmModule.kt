package com.example.data_cats.realm

import io.realm.annotations.RealmModule

@RealmModule(library = true, classes = [CatRealmObject::class])
class CatRealmModule