package com.example.aviaapp.di

import android.content.Context
import com.example.aviaapp.di.modules.ApiModule
import com.example.aviaapp.di.modules.NetworkModule
import com.example.aviaapp.di.modules.SharedPreferencesModule
import com.example.aviaapp.di.modules.ViewModelModule
import com.example.aviaapp.presentation.fragment.AllTicketsFragment
import com.example.aviaapp.presentation.fragment.MenuFragment
import com.example.aviaapp.presentation.fragment.SearchChosenFragment
import com.example.aviaapp.presentation.fragment.SearchFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class, SharedPreferencesModule::class, ApiModule::class, NetworkModule::class, DataBaseModule::class])
interface ApplicationComponent {

    fun inject(fragment: MenuFragment)
    fun inject(fragment: SearchChosenFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: AllTicketsFragment)
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}