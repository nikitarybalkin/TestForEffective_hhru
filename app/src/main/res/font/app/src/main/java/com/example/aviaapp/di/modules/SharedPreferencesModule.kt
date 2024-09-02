package com.example.aviaapp.di.modules

import com.example.aviapp.data.repositryImpls.SharedPreferenceRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {

    @Provides
    fun providesSharedPreferencesRepository(impl: SharedPreferenceRepositoryImpl): com.example.aviaapp.domain.repositories.SharedPreferenceRepository {
        return impl
    }

}