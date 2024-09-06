package com.example.vacancies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.domain.VocationsUseCase
import com.example.network.domain.model.VocationsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResponseViewModel @Inject constructor(private val vocationsUseCase: VocationsUseCase) : ViewModel() {

    val vacancies: MutableStateFlow<VocationsModel?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            vacancies.value = vocationsUseCase.getVocations()
        }
    }
}