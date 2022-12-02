package com.taranovegor91.notesdemoapplication.app

import android.app.Application

import com.taranovegor91.notesdemoapplication.data.MainDb

lateinit var mainDb: MainDb
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        mainDb = MainDb.getDb(applicationContext)
    }
}