package com.shogosensui.pinfeed

import android.app.Application

class MainApplication : Application() {
    companion object {
        lateinit var preference: Preference
    }

    override fun onCreate() {
        super.onCreate()
        preference = Preference(this)
    }
}