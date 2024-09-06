package com.example.testforeffectivehhru.di

import com.example.testforeffectivehhru.presentation.EntryRouterImpl
import com.example.testforeffectivehhru.presentation.VacanciesRouterImpl
import com.example.vacancies.presentation.VacanciesRouter
import com.feature.entry.presentation.EntryRouter
import dagger.Module
import dagger.Provides

@Module
class RouterModule {

    @Provides
    fun providesEntryRouter(entryRouterImpl: EntryRouterImpl): EntryRouter {
        return entryRouterImpl
    }

    @Provides
    fun providesVacanciesRouter(vacanciesRouterImpl: VacanciesRouterImpl): VacanciesRouter {
        return vacanciesRouterImpl
    }

}