package com.example.aviaapp.di.modules


import com.example.aviapp.data.dataSources.AllTicketsRemoteDataSource
import com.example.aviapp.data.dataSources.AllTicketsRemoteDataSourceImpl
import com.example.aviapp.data.dataSources.OffersRemoteDataSource
import com.example.aviapp.data.dataSources.OffersRemoteDataSourceIMpl
import com.example.aviapp.data.dataSources.TicketsRemoteDataSource
import com.example.aviapp.data.dataSources.TicketsRemoteDataSourceImpl
import com.example.aviapp.data.repositryImpls.AllTicketsRepositoryImpl
import com.example.aviapp.data.repositryImpls.OffersRepositoryImpl
import com.example.aviapp.data.repositryImpls.TicketsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class ApiModule {

    @Provides
    fun providesOffersRemoteDataSource(offersRemoteDataSourceIMpl: OffersRemoteDataSourceIMpl): OffersRemoteDataSource {
        return offersRemoteDataSourceIMpl
    }

    @Provides
    fun providesOffersRepository(offersRepositoryImpl: OffersRepositoryImpl): com.example.aviaapp.domain.repositories.OffersRepository {
        return offersRepositoryImpl
    }

    @Provides
    fun providesTicketsRemoteDataSource(ticketsRemoteDataSourceImpl: TicketsRemoteDataSourceImpl): TicketsRemoteDataSource {
        return ticketsRemoteDataSourceImpl
    }

    @Provides
    fun providesTicketsRepository(ticketsRepositoryImpl: TicketsRepositoryImpl): com.example.aviaapp.domain.repositories.TicketsRepository {
        return ticketsRepositoryImpl
    }

    @Provides
    fun providesAllTicketsRemoteDataSource(allTicketsRemoteDataSourceImpl: AllTicketsRemoteDataSourceImpl): AllTicketsRemoteDataSource {
        return allTicketsRemoteDataSourceImpl
    }

    @Provides
    fun providesAllTicketsRepository(allTicketsRepositoryImpl: AllTicketsRepositoryImpl): com.example.aviaapp.domain.repositories.AllTicketsRepository {
        return allTicketsRepositoryImpl
    }
}