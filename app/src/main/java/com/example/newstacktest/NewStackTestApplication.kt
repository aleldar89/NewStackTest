package com.example.newstacktest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm

@HiltAndroidApp
class NewStackTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}