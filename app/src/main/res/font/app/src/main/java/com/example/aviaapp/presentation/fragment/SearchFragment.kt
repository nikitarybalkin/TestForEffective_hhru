package com.example.aviaapp.presentation.fragment

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aviaapp.R
import com.example.aviaapp.databinding.DialogSearchBinding
import com.example.aviaapp.di.App
import com.example.aviaapp.di.ViewModelFactory
import com.example.aviaapp.presentation.viewModel.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class SearchFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "SearchFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
    }

    private lateinit var binding: DialogSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            binding.ed1FromWhere.setText(arguments?.getString("name_from_where"))
            binding.ed2ToWhere.setText(arguments?.getString("name_to_where"))
        }

        binding.zone1.setOnClickListener {
            binding.ed2ToWhere.setText(binding.tvArrives1.text)
            var bundleTo = Bundle()
            bundleTo.putString("name_from_where", binding.ed1FromWhere.text.toString())
            bundleTo.putString("name_to_where", binding.tvArrives1.text.toString())
            findNavController().navigate(R.id.action_searchFragment_to_searchChosenFragment, bundleTo)
        }

        binding.zone2.setOnClickListener {
            binding.ed2ToWhere.setText(binding.tvArrives2.text)
            binding.ed2ToWhere.setText(binding.tvArrives1.text)
            var bundleTo = Bundle()
            bundleTo.putString("name_from_where", binding.ed1FromWhere.text.toString())
            bundleTo.putString("name_to_where", binding.tvArrives2.text.toString())
            findNavController().navigate(R.id.action_searchFragment_to_searchChosenFragment, bundleTo)
        }

        binding.zone3.setOnClickListener {
            binding.ed2ToWhere.setText(binding.tvArrives3.text)
            binding.ed2ToWhere.setText(binding.tvArrives1.text)
            var bundleTo = Bundle()
            bundleTo.putString("name_from_where", binding.ed1FromWhere.text.toString())
            bundleTo.putString("name_to_where", binding.tvArrives3.text.toString())
            findNavController().navigate(R.id.action_searchFragment_to_searchChosenFragment, bundleTo)
        }

    }
}