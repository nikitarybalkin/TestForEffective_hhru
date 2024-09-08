package com.example.database.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.domain.VacanciesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VacanciesDao {

    @Query("SELECT * FROM vacanciesTable")
    fun getAll(): Flow<List<VacanciesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(table: VacanciesEntity)

    @Query("DELETE FROM vacanciesTable")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(table: VacanciesEntity)


}