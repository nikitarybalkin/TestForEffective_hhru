package com.example.vacancies.di

import com.example.vacancies.presentation.FullVacancyFragment
import com.example.vacancies.presentation.MainFragment
import com.example.vacancies.presentation.SendFragment
import dagger.Subcomponent

@Subcomponent
interface VacanciesComponent {

    fun inject(mainFragment: MainFragment)
    fun inject(fullVacancyFragment: FullVacancyFragment)
    fun inject(sendFragment: SendFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): VacanciesComponent
    }
}