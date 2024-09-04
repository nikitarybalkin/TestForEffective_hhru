package com.example.network.data

interface VocationsDataSource {

    suspend fun getVocations(): Vocations
}