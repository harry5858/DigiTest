package com.test.digiitunes

import android.app.Application
import com.test.digiitunes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DigiApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DigiApp.applicationContext)
            modules(appModule)
        }
    }
}