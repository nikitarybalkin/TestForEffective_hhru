package com.example.vacancies.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.di.ViewModelFactory
import com.example.network.data.Vocation
import com.example.vacancies.R
import com.example.vacancies.databinding.FragmentMainBinding
import com.example.vacancies.di.VacanciesComponent
import com.example.vacancies.di.VacanciesComponentProvider
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var vacanciesRouter: VacanciesRouter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var vacancyComponent: VacanciesComponent
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        vacancyComponent =
            (requireActivity().applicationContext as VacanciesComponentProvider).provideVacanciesComponent()
        vacancyComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.vacancies.collect { vacancies ->
                Log.d("LOL", "collect")
                if (vacancies != null) {
                    binding.rvQuickFilters.adapter = QuickFiltersAdapter(vacancies.offers)
                    val shortList: MutableList<Vocation> = mutableListOf()
                    for (i in 0..2) {
                        shortList.add(vacancies.vacancies[i])
                    }
                    viewModel.showAllVacancies.collect{
                        if (!it) {
                            binding.etVacancy.hint = resources.getString(com.example.core.R.string.position_keywords)
                            binding.tvNumberVacancies.visibility = View.GONE
                            binding.tvByCompliance.visibility = View.GONE
                            binding.icUpDown.visibility = View.GONE
                            binding.rvQuickFilters.visibility = View.VISIBLE
                            binding.tvVacanciesForYou.visibility = View.VISIBLE
                            binding.bMoreVocations.visibility = View.VISIBLE
                            val layoutParams = binding.rvVacancies.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.setMargins(0,32,0, 24)
                            binding.rvVacancies.layoutParams = layoutParams
                            binding.icBack.visibility = View.GONE
                            binding.icLoupe.visibility = View.VISIBLE
                            binding.rvVacancies.adapter = VacancyAdapter(
                                shortList,
                                goToFullVacancyPage = {numb -> goToFullVacancyPage(numb)}

                            )
                            binding.bMoreVocations.text = resources.getQuantityString(
                                com.example.core.R.plurals.more_vacancies,
                                vacancies.vacancies.size,
                                vacancies.vacancies.size
                            )
                        } else {
                            binding.etVacancy.hint = resources.getString(com.example.core.R.string.position_suitable_vacancies)
                            binding.tvNumberVacancies.visibility = View.VISIBLE
                            binding.tvNumberVacancies.text = resources.getQuantityString(com.example.core.R.plurals.quantity_vacancies, vacancies.vacancies.size, vacancies.vacancies.size)
                            binding.tvByCompliance.visibility = View.VISIBLE
                            binding.icUpDown.visibility = View.VISIBLE
                            binding.rvQuickFilters.visibility = View.GONE
                            binding.tvVacanciesForYou.visibility = View.GONE
                            binding.bMoreVocations.visibility = View.GONE
                            binding.rvVacancies.adapter = VacancyAdapter(
                                vacancies.vacancies,
                                goToFullVacancyPage = {numb -> goToFullVacancyPage(numb)}
                            )
                            val layoutParams = binding.rvVacancies.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.setMargins(0,140,0, 24)
                            binding.rvVacancies.layoutParams = layoutParams
                            binding.icBack.visibility = View.VISIBLE
                            binding.icLoupe.visibility = View.GONE
                        }
                    }


                }
            }
        }
        binding.icBack.setOnClickListener {
            Log.d("LOL", "switch false")
            viewModel.switchScreen(false)
        }
        binding.bMoreVocations.setOnClickListener {
            viewModel.switchScreen(true)
        }
    }

    private fun goToFullVacancyPage(number: Int) {
        vacanciesRouter.goToVacancyPage(this, number)
    }
}