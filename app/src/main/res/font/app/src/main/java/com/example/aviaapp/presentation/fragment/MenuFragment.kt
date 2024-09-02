package com.example.aviaapp.presentation.fragment

import android.content.Context
import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aviaapp.R
import com.example.aviaapp.databinding.FragmentMenuBinding
import com.example.aviaapp.di.App
import com.example.aviaapp.di.ViewModelFactory
import com.example.aviaapp.domain.model.CityModel
import com.example.aviaapp.utils.Converters
import com.example.aviaapp.presentation.adapter.MusicallyAdapter
import com.example.aviaapp.presentation.viewModel.MenuViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentMenuBinding
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        (requireActivity().applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.getData("city_from_where") != null) binding.ed1FromWhere.setText(
            viewModel.getData(
                "city_from_where"
            )
        )

        viewModel.getOffers()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.offers.collect {
                if (it != null) {
                    var adapterMusically = MusicallyAdapter(
                        context = requireContext(),
                        offer = it.offers,
                        images = listOf(R.drawable.musician1, R.drawable.musician2, R.drawable.musician3)
                    )
                    binding.rvMusically.adapter = adapterMusically
                }

            }
        }

        binding.ed1FromWhere.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.saveData(
                    key = "city_from_where",
                    value = binding.ed1FromWhere.text.toString()
                )
            }

        })

        binding.ed2ToWhere.setOnClickListener {
            if (binding.ed1FromWhere.text.toString() != "") {
                var bundle = Bundle()
                bundle.putString("name_from_where", binding.ed1FromWhere.text.toString())
                findNavController().navigate(R.id.action_mainFragment2_to_searchFragment, bundle)
            } else findNavController().navigate(R.id.action_mainFragment2_to_searchFragment)


        }
    }
}