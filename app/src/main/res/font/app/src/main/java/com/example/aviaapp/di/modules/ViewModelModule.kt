package com.example.aviaapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.aviaapp.presentation.viewModel.AllTicketsViewModel
import com.example.aviaapp.presentation.viewModel.MenuViewModel
import com.example.aviaapp.presentation.viewModel.SearchChosenViewModel
import com.example.aviaapp.presentation.viewModel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Singleton

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("MenuViewModel")
    @Binds
    fun bindsMenuViewModel(impl: MenuViewModel): ViewModel

    @IntoMap
    @StringKey("SearchViewModel")
    @Binds
    fun bindsSearchViewModel(impl: SearchViewModel): ViewModel

    @IntoMap
    @StringKey("SearchChosenViewModel")
    @Binds
    fun bindsSearchChosenViewModel(impl: SearchChosenViewModel): ViewModel

    @IntoMap
    @StringKey("AllTicketsViewModel")
    @Binds
    fun bindsAllTicketsViewModel(impl: AllTicketsViewModel): ViewModel

}