package com.example.aviaapp.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aviaapp.domain.model.CityEntity
import com.example.aviapp.data.dao.CityDao

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)

abstract class AppDataBase : RoomDatabase() {
    abstract fun cityDao() : CityDao
}