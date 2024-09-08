package com.example.testforeffectivehhru.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.data.VacanciesDao
import com.example.database.domain.VacanciesEntity
import com.example.database.utils.Converters

@Database(entities = [VacanciesEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)

abstract class AppDataBase : RoomDatabase() {
    abstract fun vacanciesDao() : VacanciesDao
}