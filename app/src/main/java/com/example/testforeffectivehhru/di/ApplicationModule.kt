package com.example.testforeffectivehhru.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import com.example.network.di.NetworkModule
import com.feature.entry.di.EntryComponent
import com.feature.entry.presentation.EntryRouter

@Component(modules = [NetworkModule::class, ViewModelModule::class, RouterModule::class])
interface ApplicationComponent {
    /*fun inject(fragment: MenuFragment)
    fun inject(fragment: SearchChosenFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: AllTicketsFragment)*/
    fun loginComponent(): EntryComponent.Factory
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}