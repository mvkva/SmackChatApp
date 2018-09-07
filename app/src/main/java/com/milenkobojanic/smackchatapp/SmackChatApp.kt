package com.milenkobojanic.smackchatapp

import android.app.Application
import com.milenkobojanic.smackchatapp.utilities.SharedPrefs

class SmackChatApp: Application() {

    companion object {
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {

        prefs = SharedPrefs(applicationContext)
        super.onCreate()
    }
}