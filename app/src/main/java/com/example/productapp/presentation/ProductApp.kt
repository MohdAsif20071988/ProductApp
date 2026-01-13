package com.example.productapp.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProductApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}