package com.example.network.domain

import com.example.network.domain.model.VocationsModel
import javax.inject.Inject

class VocationsUseCase @Inject constructor(protected val vocationsRepository: VocationsRepository) {

    suspend fun getVocations(): VocationsModel {
        return vocationsRepository.getVocations()
    }

}