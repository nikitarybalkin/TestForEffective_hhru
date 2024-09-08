package com.example.vacancies.presentation

import android.util.Log
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

class MainViewModel @Inject constructor(
    private val vocationsUseCase: VocationsUseCase,
    private val vacancyUseCase: VacancyUseCase

) : ViewModel() {

    val vacancies: MutableStateFlow<VocationsModel?> = MutableStateFlow(null)
    val showAllVacancies: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun switchScreen(value: Boolean) {
        showAllVacancies.value = value
    }
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