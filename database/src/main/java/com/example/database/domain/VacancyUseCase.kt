package com.example.database.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VacancyUseCase @Inject constructor(private val vacancyLocalRepository: VacancyLocalRepository) {

    fun getAll(): Flow<List<VacancyLocalModel>> {
        return vacancyLocalRepository.getAll()
    }

    suspend fun insert(table: VacancyLocalModel) {
        vacancyLocalRepository.insert(table)
    }

    suspend fun deleteAll() {
        vacancyLocalRepository.deleteAll()
    }

    suspend fun delete(table: VacancyLocalModel) {
        vacancyLocalRepository.delete(table)
    }



}