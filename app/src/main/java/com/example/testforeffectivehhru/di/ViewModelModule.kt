package com.example.testforeffectivehhru.di

import androidx.lifecycle.ViewModel
import com.example.vacancies.presentation.FullVacancyViewModel
import com.example.vacancies.presentation.MainViewModel
import com.example.vacancies.presentation.ResponseViewModel
import com.feature.entry.presentation.EntryViewModel
import com.feature.entry.presentation.VerificationCodeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Singleton

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("EntryViewModel")
    @Binds
    fun bindsEntryViewModel(impl: EntryViewModel): ViewModel

    @IntoMap
    @StringKey("VerificationCodeViewModel")
    @Binds
    fun bindsVerificationCodeViewModel(impl: VerificationCodeViewModel): ViewModel

    @IntoMap
    @StringKey("MainViewModel")
    @Binds
    fun bindsMainViewModel(impl: MainViewModel): ViewModel

    @IntoMap
    @StringKey("ResponseViewModel")
    @Binds
    fun bindsResponseViewModel(impl: ResponseViewModel): ViewModel

    @IntoMap
    @StringKey("FullVacancyViewModel")
    @Binds
    fun bindsFullVacancyViewModel(impl: FullVacancyViewModel): ViewModel



}