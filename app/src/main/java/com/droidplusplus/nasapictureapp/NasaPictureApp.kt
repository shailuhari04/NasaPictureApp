package com.droidplusplus.nasapictureapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.droidplusplus.nasapictureapp.utils.isNight

class NasaPictureApp: Application() {

    override fun onCreate() {
        super.onCreate()

        dayNightModeSetUp()
    }

    private fun dayNightModeSetUp() {
        // Get UI mode and set
        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }
}