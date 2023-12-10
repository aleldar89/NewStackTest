package com.example.data_dogs.realm

import io.realm.annotations.RealmModule

@RealmModule(library = true, classes = [DogRealmObject::class])
class DogRealmModule