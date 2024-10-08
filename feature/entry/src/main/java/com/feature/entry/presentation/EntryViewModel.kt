package com.feature.entry.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.domain.VacancyUseCase
import com.example.network.domain.VocationsUseCase
import com.example.network.domain.model.VocationsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EntryViewModel @Inject constructor(
    private val vocationsUseCase: VocationsUseCase,
) : ViewModel() {
    var et_text = ""
    val vocations: MutableStateFlow<VocationsModel?> = MutableStateFlow(null)

}