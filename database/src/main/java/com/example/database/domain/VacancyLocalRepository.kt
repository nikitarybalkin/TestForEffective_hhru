package com.example.database.domain

import kotlinx.coroutines.flow.Flow

interface VacancyLocalRepository {
    fun getAll(): Flow<List<VacancyLocalModel>>
    suspend fun insert(table: VacancyLocalModel)
    suspend fun deleteAll()
    suspend fun delete(table: VacancyLocalModel)
}