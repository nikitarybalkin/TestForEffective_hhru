package com.example.network.domain

import com.example.network.domain.model.VocationsModel

interface VocationsRepository {
    suspend fun getVocations(): VocationsModel
}