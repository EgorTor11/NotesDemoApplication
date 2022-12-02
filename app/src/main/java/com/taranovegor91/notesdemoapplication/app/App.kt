package com.taranovegor91.notesdemoapplication

import android.app.Application

lateinit var mainDb: MainDb
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        mainDb = MainDb.getDb(applicationContext)
    }
}