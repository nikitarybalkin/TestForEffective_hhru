package com.example.testforeffectivehhru.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.testforeffectivehhru.R
import javax.inject.Inject
import com.feature.entry.presentation.EntryRouter

class EntryRouterImpl @Inject constructor(): EntryRouter {
    override fun goToVerificationFragment(fragment: Fragment, email: String) {
        val bundle = Bundle()
        bundle.putString("email", email)
        findNavController(fragment).navigate(R.id.action_entryFragment_to_verificationCodeFragment, bundle)
    }

    override fun goToMainFragment(fragment: Fragment) {
        findNavController(fragment).navigate(R.id.action_verificationCodeFragment_to_mainFragment)
    }
}