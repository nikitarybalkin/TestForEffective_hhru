package com.example.network.di

import com.example.network.data.VocationsApi
import com.example.network.data.VocationsDataSource
import com.example.network.data.VocationsDataSourceImpl
import com.example.network.data.VocationsRepositoryImpl
import com.example.network.domain.VocationsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesVocationsApi(retrofit: Retrofit): VocationsApi {
        return retrofit.create(VocationsApi::class.java)
    }

    @Provides
    fun providesVocationsDataSource(vocationsDataSourceImpl: VocationsDataSourceImpl): VocationsDataSource {
        return vocationsDataSourceImpl
    }

    @Provides
    fun providesVocationsRepository(vocationsRepositoryImpl: VocationsRepositoryImpl): VocationsRepository {
        return vocationsRepositoryImpl
    }
}

