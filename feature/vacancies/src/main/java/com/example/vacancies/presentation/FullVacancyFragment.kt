package com.example.vacancies.presentation

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.di.ViewModelFactory
import com.example.core.utils.Converters
import com.example.network.data.Vocation
import com.example.vacancies.R
import com.example.vacancies.databinding.FragmentFullVacancyBinding
import com.example.vacancies.di.VacanciesComponent
import com.example.vacancies.di.VacanciesComponentProvider
import javax.inject.Inject

class FullVacancyFragment : Fragment() {

    @Inject
    lateinit var vacanciesRouter: VacanciesRouter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var vacancyComponent: VacanciesComponent
    private lateinit var binding: FragmentFullVacancyBinding
    private lateinit var viewModel: FullVacancyViewModel
    private lateinit var vacancy: Vocation


    override fun onAttach(context: Context) {
        vacancyComponent =
            (requireActivity().applicationContext as VacanciesComponentProvider).provideVacanciesComponent()
        vacancyComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[FullVacancyViewModel::class.java]
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFullVacancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val number = arguments?.getInt("numberOfVacancy")
        var fragment = this
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.vacancies.collect {
                if (it != null && number != null) {
                    vacancy = it.vacancies[number]
                    binding.let {
                        it.tvVacancy.text = vacancy.title
                        it.tvSalary.text = vacancy.salary.full
                        it.tvExperience.text = it.root.context.getString(
                            com.example.core.R.string.required_experience,
                            vacancy.experience.text
                        )
                        it.tvSchedule.text = Converters().convertSchedules(vacancy.schedules)
                        if (vacancy.appliedNumber != null) {
                            it.tvAlreadyRespond.text = it.root.resources.getQuantityString(
                                com.example.core.R.plurals.people_already_responded,
                                vacancy.appliedNumber,
                                vacancy.appliedNumber
                            )
                        } else it.cvAlreadyRespond.visibility = View.GONE
                        if (vacancy.lookingNumber != null) {
                            it.tvNowLooking.text = it.root.resources.getQuantityString(
                                com.example.core.R.plurals.now_looking,
                                vacancy.lookingNumber,
                                vacancy.lookingNumber
                            )
                        } else it.cvNowLooking.visibility = View.GONE
                        it.tvCompany.text = vacancy.company
                        it.tvAddress.text = "${vacancy.address.town}, ${vacancy.address.street}, ${vacancy.address.house}"
                        if (vacancy.description != null) {
                            it.tvDescription.text = vacancy.description
                        } else it.tvDescription.visibility = View.GONE
                        it.tvTasks.text = vacancy.responsibilities
                        it.rvQuestions.adapter = QuickQuestionsAdapter(
                            vacancy.questions,
                            goToSendPage = {question -> goToSendPage(question)}
                        )
                        it.icFavorite.setOnClickListener {
                            viewModel.addToFavorites(vacancy)
                            binding.icFavorite.setImageResource(com.example.core.R.drawable.ic_heart_activated)
                        }
                        it.bRespond.setOnClickListener {
                            vacanciesRouter.goToSendPage(fragment, question = null, vacancy = vacancy.title)
                        }
                        it.icBack.setOnClickListener {
                            vacanciesRouter.goFromVacancyToVacancies(fragment)
                        }
                    }
                }
            }
        }

    }
    private fun goToSendPage(question: String?) {
        vacanciesRouter.goToSendPage(this, question, vacancy.title)
    }
}