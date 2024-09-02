package com.example.aviaapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aviaapp.domain.usecases.GetTicketsUseCase
import com.example.aviaapp.domain.model.TicketsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchChosenViewModel @Inject constructor(
    private val getTicketsUseCase: com.example.aviaapp.domain.usecases.GetTicketsUseCase,
) : ViewModel() {

    var tickets: MutableStateFlow<com.example.aviaapp.domain.model.TicketsModel?> = MutableStateFlow(null)
    var name_from_where: String? = null
    var name_to_where: String? = null

    fun getTickets() {
        viewModelScope.launch {
            try {
                tickets.value = getTicketsUseCase.getTickets()
            } catch (e: Exception) {
            }
        }

    }

}