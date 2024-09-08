package com.example.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.domain.VacancyLocalModel
import com.example.database.domain.VacancyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val vacancyUseCase: VacancyUseCase
) : ViewModel() {
    var vacancies: MutableStateFlow<List<VacancyLocalModel>?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            vacancyUseCase.getAll().collect {
                vacancies.value = it
            }
        }


    }
}