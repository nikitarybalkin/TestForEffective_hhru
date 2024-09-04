package com.feature.entry.presentation

import androidx.fragment.app.Fragment

interface EntryRouter {
    fun goToVerificationFragment(fragment: Fragment, email: String)

    fun goToMainFragment(fragment: Fragment)
}