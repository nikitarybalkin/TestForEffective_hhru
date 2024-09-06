package com.example.vacancies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.domain.VocationsUseCase
import com.example.network.domain.model.VocationsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val vocationsUseCase: VocationsUseCase) : ViewModel() {

    val vacancies: MutableStateFlow<VocationsModel?> = MutableStateFlow(null)
    val showAllVacancies: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun switchScreen(value: Boolean) {
        showAllVacancies.value = value
    }

    init {
        viewModelScope.launch {
            vacancies.value = vocationsUseCase.getVocations()
        }
    }

}