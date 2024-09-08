package com.example.vacancies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.domain.VacancyLocalModel
import com.example.database.domain.VacancyUseCase
import com.example.network.data.Vocation
import com.example.network.domain.VocationsUseCase
import com.example.network.domain.model.VocationsModel
import com.example.vacancies.utils.Converter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FullVacancyViewModel @Inject constructor(
    private val vocationsUseCase: VocationsUseCase,
    private val vacancyUseCase: VacancyUseCase
) : ViewModel() {
    val vacancies: MutableStateFlow<VocationsModel?> = MutableStateFlow(null)

    fun addToFavorites(table: Vocation) {
        viewModelScope.launch {
            vacancyUseCase.insert(Converter().convertFromModelToLocalData(table))
        }
    }

    init {
        viewModelScope.launch {
            vacancies.value = vocationsUseCase.getVocations()
        }
    }
}