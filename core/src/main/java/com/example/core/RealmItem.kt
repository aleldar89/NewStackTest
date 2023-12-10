package com.example.core

interface RealmItem<D> {
    var id: String
    fun toDto(): D
}