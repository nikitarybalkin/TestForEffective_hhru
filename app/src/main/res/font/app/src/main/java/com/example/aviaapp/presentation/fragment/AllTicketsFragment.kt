package com.example.aviaapp.presentation.fragment

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aviaapp.R
import com.example.aviaapp.databinding.FragmentAllTicketsBinding
import com.example.aviaapp.databinding.FragmentMenuBinding
import com.example.aviaapp.di.App
import com.example.aviaapp.di.ViewModelFactory
import com.example.aviaapp.presentation.adapter.TicketsAdapter
import com.example.aviaapp.presentation.viewModel.AllTicketsViewModel
import com.example.aviaapp.presentation.viewModel.MenuViewModel
import com.example.aviaapp.presentation.viewModel.SearchChosenViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllTicketsFragment : Fragment() {

    companion object {
        fun newInstance() = AllTicketsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentAllTicketsBinding
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AllTicketsViewModel::class.java]
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCity.text = "${arguments?.getString("name_from_where")} - ${arguments?.getString("name_to_where")}"
        viewModel.getAllTickets()
        binding.backBlue.setOnClickListener {
            var bundleTo = Bundle()
            bundleTo.putString("name_from_where", arguments?.getString("name_from_where"))
            bundleTo.putString("name_to_where", arguments?.getString("name_to_where"))
            findNavController().navigate(R.id.action_allTicketsFragment_to_searchChosenFragment, bundleTo)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allTicketsModel.collect {
                if (it != null) {
                    if (it.tickets.isNotEmpty()) {
                        var adapter = TicketsAdapter(it.tickets)
                        binding.rvTicketCard.adapter = adapter
                    }
                }
            }
        }

    }
}