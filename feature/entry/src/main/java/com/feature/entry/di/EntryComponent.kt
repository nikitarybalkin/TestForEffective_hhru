package com.feature.entry.di

import com.feature.entry.presentation.EntryFragment
import com.feature.entry.presentation.VerificationCodeFragment
import dagger.Subcomponent

@Subcomponent
interface EntryComponent {

    fun inject(entryFragment: EntryFragment)
    fun inject(verificationCodeFragment: VerificationCodeFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): EntryComponent
    }
}