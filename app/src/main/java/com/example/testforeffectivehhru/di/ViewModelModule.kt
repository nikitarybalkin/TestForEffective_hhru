package com.example.testforeffectivehhru.di

import androidx.lifecycle.ViewModel
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

    /*@IntoMap
    @StringKey("SearchChosenViewModel")
    @Binds
    fun bindsSearchChosenViewModel(impl: SearchChosenViewModel): ViewModel

    @IntoMap
    @StringKey("AllTicketsViewModel")
    @Binds
    fun bindsAllTicketsViewModel(impl: AllTicketsViewModel): ViewModel*/

}