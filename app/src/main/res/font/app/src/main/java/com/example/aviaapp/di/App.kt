package com.example.aviaapp.di

import android.app.Application

class App: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}