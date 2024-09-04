package com.example.network.data

import com.example.network.domain.VocationsRepository
import com.example.network.domain.model.VocationsModel
import com.example.network.domain.model.mapToDomain
import javax.inject.Inject

class VocationsRepositoryImpl @Inject constructor (protected val vocationsDataSource: VocationsDataSource): VocationsRepository {
    override suspend fun getVocations(): VocationsModel {
        return vocationsDataSource.getVocations().mapToDomain()
    }

}