package com.example.testforeffectivehhru.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.testforeffectivehhru.R
import com.example.vacancies.presentation.VacanciesRouter
import javax.inject.Inject

class VacanciesRouterImpl @Inject constructor(): VacanciesRouter {
    override fun goToVacancyPage(fragment: Fragment, number: Int) {
        val bundle = Bundle()
        bundle.putInt("numberOfVacancy", number)
        findNavController(fragment).navigate(R.id.action_mainFragment_to_fullVacancyFragment, bundle)
    }

    override fun goToSendPage(fragment: Fragment, question: String?, vacancy: String) {
        val bundle = Bundle()
        bundle.putString("question", question)
        bundle.putString("vacancy", vacancy)
        findNavController(fragment).navigate(R.id.action_fullVacancyFragment_to_sendFragment, bundle)
    }

    override fun goFromSendToVacancyPage(fragment: Fragment) {
        findNavController(fragment).navigate(R.id.action_sendFragment_to_fullVacancyFragment)
    }

    override fun goFromVacancyToVacancies(fragment: Fragment) {
        findNavController(fragment).navigate(R.id.action_fullVacancyFragment_to_mainFragment)
    }
}