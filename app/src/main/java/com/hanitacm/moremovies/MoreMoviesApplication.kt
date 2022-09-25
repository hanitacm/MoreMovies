package com.hanitacm.moremovies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoreMoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}