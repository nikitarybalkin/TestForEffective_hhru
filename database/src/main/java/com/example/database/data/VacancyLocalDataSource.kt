package com.example.database.data

import com.example.database.domain.VacanciesEntity
import kotlinx.coroutines.flow.Flow

interface VacancyLocalDataSource {

    fun getAll(): Flow<List<VacanciesEntity>>

    suspend fun insert(table: VacanciesEntity)

    suspend fun deleteAll()

    suspend fun delete(table: VacanciesEntity)

}