package com.example.network.data

import javax.inject.Inject

class VocationsDataSourceImpl @Inject constructor(protected val vocationsApi: VocationsApi): VocationsDataSource {
    override suspend fun getVocations(): Vocations {
        return vocationsApi.getVocations()
    }
}