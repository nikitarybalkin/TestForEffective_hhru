package com.example.database.data

import com.example.database.domain.VacancyLocalModel
import com.example.database.domain.VacancyLocalRepository
import com.example.database.domain.mapToData
import com.example.database.domain.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VacancyLocalRepositoryImpl @Inject constructor(private val vacancyLocalDataSource: VacancyLocalDataSource): VacancyLocalRepository {
    override fun getAll(): Flow<List<VacancyLocalModel>> {
        return vacancyLocalDataSource.getAll().map { list ->  list.map { it.mapToDomain() } }
    }

    override suspend fun insert(table: VacancyLocalModel) {
        vacancyLocalDataSource.insert(table.mapToData())
    }

    override suspend fun deleteAll() {
        vacancyLocalDataSource.deleteAll()
    }

    override suspend fun delete(table: VacancyLocalModel) {
        vacancyLocalDataSource.delete(table.mapToData())
    }


}