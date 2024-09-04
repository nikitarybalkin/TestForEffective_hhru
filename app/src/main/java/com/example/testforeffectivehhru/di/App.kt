package com.example.testforeffectivehhru.di

import android.app.Application
import com.feature.entry.di.EntryComponent
import com.feature.entry.di.EntryComponentProvider

class App: Application(), EntryComponentProvider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }


    override fun provideEntryComponent(): EntryComponent {
        return component.loginComponent().create()
    }

}