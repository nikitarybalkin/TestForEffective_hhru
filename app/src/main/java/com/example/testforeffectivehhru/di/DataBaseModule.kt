package com.example.testforeffectivehhru.di

import android.content.Context
import androidx.room.Room
import com.example.database.data.VacanciesDao
import com.example.database.data.VacancyLocalDataSource
import com.example.database.data.VacancyLocalDataSourceImpl
import com.example.database.data.VacancyLocalRepositoryImpl
import com.example.database.domain.VacancyLocalRepository
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
    fun provideVacancyDao(db: AppDataBase): VacanciesDao {
        return db.vacanciesDao()
    }
    @Provides
    fun provideVacancyRepository(vacancyLocalRepositoryImpl: VacancyLocalRepositoryImpl): VacancyLocalRepository {
        return vacancyLocalRepositoryImpl
    }
    @Provides
    fun provideVacancyDataSource(vacancyLocalDataSourceImpl: VacancyLocalDataSourceImpl): VacancyLocalDataSource {
        return vacancyLocalDataSourceImpl
    }
}