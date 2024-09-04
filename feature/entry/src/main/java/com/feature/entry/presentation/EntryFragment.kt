package com.feature.entry.presentation

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.di.ViewModelFactory
import com.example.core.utils.Checkers
import com.feature.entry.R
import com.feature.entry.databinding.FragmentEntryBinding
import com.feature.entry.di.EntryComponent
import com.feature.entry.di.EntryComponentProvider
import javax.inject.Inject

class EntryFragment : Fragment() {

    companion object {
        fun newInstance() = EntryFragment()
    }
    @Inject
    lateinit var entryRouter: EntryRouter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var entryComponent: EntryComponent
    private lateinit var binding: FragmentEntryBinding
    private lateinit var viewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        entryComponent =
            (requireActivity().applicationContext as EntryComponentProvider).provideEntryComponent()
        entryComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[EntryViewModel::class.java]
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etEmailOrPhone.setText(viewModel.et_text)
        binding.etEmailOrPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("LOL", "onTextChanged")
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let { viewModel.et_text = it.toString() }
                if (viewModel.et_text != "") {
                    binding.icLetter.visibility = View.GONE
                    binding.bContinue.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            com.example.core.R.color.special_blue
                        )
                    )
                    binding.bContinue.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            com.example.core.R.color.white
                        )
                    )
                    binding.bContinue.isEnabled = true
                } else {
                    binding.icLetter.visibility = View.VISIBLE
                    binding.bContinue.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            com.example.core.R.color.b_not_active_background
                        )
                    )
                    binding.bContinue.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            com.example.core.R.color.b_not_active_text
                        )
                    )
                    binding.bContinue.isEnabled = false
                }
                binding.icCross.visibility = View.VISIBLE
                binding.icCross.setOnClickListener {
                    binding.etEmailOrPhone.setText("")
                }
            }
        })
        binding.bContinue.setOnClickListener {
            if (Checkers().isEmailValid(viewModel.et_text)) {
                entryRouter.goToVerificationFragment(this, binding.etEmailOrPhone.text.toString())
            } else {
                binding.tvWarning.visibility = View.VISIBLE
            }
        }
    }
}