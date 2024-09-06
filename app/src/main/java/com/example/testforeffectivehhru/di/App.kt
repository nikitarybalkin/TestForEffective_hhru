package com.example.testforeffectivehhru.di

import android.app.Application
import com.example.vacancies.di.VacanciesComponent
import com.example.vacancies.di.VacanciesComponentProvider
import com.feature.entry.di.EntryComponent
import com.feature.entry.di.EntryComponentProvider

class App: Application(), EntryComponentProvider, VacanciesComponentProvider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }


    override fun provideEntryComponent(): EntryComponent {
        return component.entryComponent().create()
    }

    override fun provideVacanciesComponent(): VacanciesComponent {
        return component.vacanciesComponent().create()
    }

}