package com.development.test

import android.app.Application

class App:Application() {


    override fun onCreate() {
        super.onCreate()
        DatabaseManager(applicationContext)
    }

}