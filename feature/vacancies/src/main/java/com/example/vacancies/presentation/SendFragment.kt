package com.example.vacancies.presentation

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.ViewModelFactory
import com.example.vacancies.R
import com.example.vacancies.databinding.FragmentSendBinding
import com.example.vacancies.di.VacanciesComponent
import com.example.vacancies.di.VacanciesComponentProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class SendFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var vacanciesRouter: VacanciesRouter
    private lateinit var vacancyComponent: VacanciesComponent
    private lateinit var binding: FragmentSendBinding
    override fun onAttach(context: Context) {
        vacancyComponent =
            (requireActivity().applicationContext as VacanciesComponentProvider).provideVacanciesComponent()
        vacancyComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val question = arguments?.getString("question")
        binding.tvNameOfResume.text = arguments?.getString("vacancy")
        if (question != null) {
            binding.tvAddAccompanying.visibility = View.GONE
            binding.etYourAccompanying.visibility = View.VISIBLE
            binding.etYourAccompanying.setText(question)
            binding.bRespond.setOnClickListener {
                vacanciesRouter.goFromSendToVacancyPage(this)
            }
        } else {
            binding.tvAddAccompanying.setOnClickListener {
                binding.tvAddAccompanying.visibility = View.GONE
                binding.etYourAccompanying.visibility = View.VISIBLE
                binding.bRespond.setOnClickListener {
                    vacanciesRouter.goFromSendToVacancyPage(this)
                }
            }
        }

    }
}