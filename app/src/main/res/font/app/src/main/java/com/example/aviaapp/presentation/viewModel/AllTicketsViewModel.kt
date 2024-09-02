package com.example.aviaapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.aviaapp.domain.usecases.GetAllTicketsUseCase
import com.example.aviaapp.domain.model.AllTicketsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllTicketsViewModel @Inject constructor(private val getAllTicketsUseCase: com.example.aviaapp.domain.usecases.GetAllTicketsUseCase): ViewModel() {

    var allTicketsModel: MutableStateFlow<com.example.aviaapp.domain.model.AllTicketsModel?> = MutableStateFlow(null)
    fun getAllTickets() {
        viewModelScope.launch {
            try {
                allTicketsModel.value = getAllTicketsUseCase.getAllTickets()
            }
            catch (e: Exception) {}
        }
    }
}