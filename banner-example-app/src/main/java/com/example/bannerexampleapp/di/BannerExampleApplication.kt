package com.example.bannerexampleapp.di

import android.app.Application
import com.example.bannerexampleapp.core.logger.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BannerExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Logger.init()
    }
}