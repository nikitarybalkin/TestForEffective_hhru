package com.example.aviaapp.di

import android.content.Context
import androidx.room.Room

import com.example.aviapp.data.dao.CityDao
import com.example.aviapp.data.dataSources.CityLocalDataSource
import com.example.aviapp.data.dataSources.CityLocalDataSourceImpl
import com.example.aviapp.data.repositryImpls.CityRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {
    @Provides
    fun provideDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "db")
            .fallbackToDestructiveMigration().build()
    }
    @Provides
    fun provideCityDao(db: AppDataBase): CityDao {
        return db.cityDao()
    }
    @Provides
    fun provideCityRepository(cityRepositoryImpl: CityRepositoryImpl): com.example.aviaapp.domain.repositories.CityRepository {
        return cityRepositoryImpl
    }
    @Provides
    fun provideCityDataSource(cityLocalDataSourceImpl: CityLocalDataSourceImpl): CityLocalDataSource {
        return cityLocalDataSourceImpl
    }
}