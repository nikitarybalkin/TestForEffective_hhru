package com.example.database.data

import com.example.database.domain.VacanciesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VacancyLocalDataSourceImpl @Inject constructor(private val vacancyDao: VacanciesDao): VacancyLocalDataSource {
    override fun getAll(): Flow<List<VacanciesEntity>> {
        return vacancyDao.getAll()
    }

    override suspend fun insert(table: VacanciesEntity) {
        vacancyDao.insert(table)
    }

    override suspend fun deleteAll() {
        vacancyDao.deleteAll()
    }

    override suspend fun delete(table: VacanciesEntity) {
        vacancyDao.delete(table)
    }

}