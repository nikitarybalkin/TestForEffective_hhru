package com.example.vacancies.presentation

import androidx.fragment.app.Fragment

interface VacanciesRouter {
    fun goToVacancyPage(fragment: Fragment, number: Int)

    fun goToSendPage(fragment: Fragment, question: String?, vacancy: String)

    fun goFromSendToVacancyPage(fragment: Fragment)

    fun goFromVacancyToVacancies(fragment: Fragment)
}