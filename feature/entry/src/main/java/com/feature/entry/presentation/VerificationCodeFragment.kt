package com.feature.entry.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.di.ViewModelFactory
import com.feature.entry.R
import com.feature.entry.databinding.FragmentVerificationCodeBinding
import com.feature.entry.di.EntryComponent
import com.feature.entry.di.EntryComponentProvider
import javax.inject.Inject

class VerificationCodeFragment : Fragment() {

    companion object {
        fun newInstance() = VerificationCodeFragment()
    }

    @Inject
    lateinit var entryRouter: EntryRouter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var entryComponent: EntryComponent
    private lateinit var binding: FragmentVerificationCodeBinding
    private lateinit var viewModel: VerificationCodeViewModel

    override fun onAttach(context: Context) {
        entryComponent =
            (requireActivity().applicationContext as EntryComponentProvider).provideEntryComponent()
        entryComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[VerificationCodeViewModel::class.java]
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerificationCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //доработать
        arguments?.getString("email")
        setupEditText(binding.etCode1, binding.etCode2)
        setupEditText(binding.etCode2, binding.etCode3)
        setupEditText(binding.etCode3, binding.etCode4)
    }

    private fun setupEditText(currentEditText: EditText, nextEditText: EditText) {
        val fragment = this
        currentEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {

                    nextEditText.requestFocus()
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        if (nextEditText == binding.etCode4) {
            binding.etCode4.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if (checkCodeEntered()) {
                        binding.bConfirm.isEnabled = true
                        binding.bConfirm.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                com.example.core.R.color.special_blue
                            )
                        )
                        binding.bConfirm.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                com.example.core.R.color.white
                            )
                        )
                        binding.bConfirm.setOnClickListener{
                            entryRouter.goToMainFragment(fragment)
                        }
                    }
                }

            })
        }
    }
    private fun checkCodeEntered(): Boolean {
        return binding.etCode4.text.toString() != "" && binding.etCode2.text.toString() != "" && binding.etCode3.text.toString() != "" && binding.etCode4.text.toString() != ""
    }
}