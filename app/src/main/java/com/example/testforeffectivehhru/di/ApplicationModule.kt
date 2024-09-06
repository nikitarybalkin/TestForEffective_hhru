package com.example.testforeffectivehhru.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import com.example.network.di.NetworkModule
import com.example.vacancies.di.VacanciesComponent
import com.feature.entry.di.EntryComponent
import com.feature.entry.presentation.EntryRouter

@Component(modules = [NetworkModule::class, ViewModelModule::class, RouterModule::class])
interface ApplicationComponent {
    fun entryComponent(): EntryComponent.Factory
    fun vacanciesComponent(): VacanciesComponent.Factory
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}