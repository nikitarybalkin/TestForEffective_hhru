package com.example.favorites.di

import com.example.favorites.presentation.FavoritesFragment
import dagger.Subcomponent

@Subcomponent
interface FavoritesComponent {

    fun inject(mainFragment: FavoritesFragment)


    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoritesComponent
    }
}