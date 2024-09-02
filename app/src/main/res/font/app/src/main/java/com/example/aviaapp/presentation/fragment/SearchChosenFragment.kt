package com.example.aviaapp.presentation.fragment

import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aviaapp.R
import com.example.aviapp.data.ButtonFunction
import com.example.aviaapp.databinding.DialogSearchBinding
import com.example.aviaapp.databinding.FragmentSearchChosenBinding
import com.example.aviaapp.di.App
import com.example.aviaapp.di.ViewModelFactory
import com.example.aviaapp.utils.Converters
import com.example.aviaapp.presentation.adapter.ButtonsAdapter
import com.example.aviaapp.presentation.viewModel.SearchChosenViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Calendar

import javax.inject.Inject

class SearchChosenFragment : Fragment() {

    companion object {
        fun newInstance() = SearchChosenFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SearchChosenViewModel::class.java]
    }

    private lateinit var binding: FragmentSearchChosenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchChosenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener {
            var bundleTo = Bundle()
            bundleTo.putString("name_from_where", binding.ed1FromWhere.text.toString())
            bundleTo.putString("name_to_where", binding.ed2ToWhere.text.toString())
            findNavController().navigate(
                R.id.action_searchChosenFragment_to_searchFragment,
                bundleTo
            )
        }
        binding.ivTurnOver.setOnClickListener {
            var from_where: String = binding.ed1FromWhere.text.toString()
            viewModel.name_to_where = from_where
            viewModel.name_from_where = binding.ed2ToWhere.text.toString()
            binding.ed1FromWhere.setText(viewModel.name_from_where)
            binding.ed2ToWhere.setText(viewModel.name_to_where)
        }
        if (arguments?.getString("name_from_where") != null) {
            binding.ed1FromWhere.setText(arguments?.getString("name_from_where"))
            viewModel.name_from_where = arguments?.getString("name_from_where")!!
        } else {
            binding.ed1FromWhere.setText(viewModel.name_from_where)
        }
        if (arguments?.getString("name_to_where") != null) {
            binding.ed2ToWhere.setText(arguments?.getString("name_to_where"))
            viewModel.name_to_where = arguments?.getString("name_to_where")!!
        } else {
            binding.ed2ToWhere.setText(viewModel.name_to_where)
        }
        binding.bLookAll.setOnClickListener {
            var bundleTo = Bundle()
            bundleTo.putString("name_from_where", binding.ed1FromWhere.text.toString())
            bundleTo.putString("name_to_where", binding.ed2ToWhere.text.toString())
            findNavController().navigate(
                R.id.action_searchChosenFragment_to_allTicketsFragment,
                bundleTo
            )
        }
        val calendar = Calendar.getInstance()
        var adapter = ButtonsAdapter(
            buttonsActions = listOf(
                com.example.aviapp.data.ButtonFunction(
                    name = getString(R.string.back),
                    action = { setDate() },
                    url = R.drawable.plus
                ),
                com.example.aviapp.data.ButtonFunction(
                    name = Converters().getDate(requireContext()),
                    action = { setDate() },
                    url = null
                ),
                com.example.aviapp.data.ButtonFunction(
                    name = getString(R.string.one_econom),
                    action = {},
                    url = R.drawable.man
                ),
                com.example.aviapp.data.ButtonFunction(
                    name = getString(R.string.filters),
                    action = {},
                    url = R.drawable.filter
                )

            ),
            context = requireContext()
        )
        binding.rvFilters.adapter = adapter
        viewModel.getTickets()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tickets.collect {
                if (it != null) {
                    if (it.tickets_offers.isNotEmpty()) {
                        binding.tvNameRed.text = it.tickets_offers[0].title
                        binding.tvPrice1.text =
                            Converters().convertPrice(it.tickets_offers[0].price.value)
                        binding.tvTime.text =
                            Converters().convertTime(it.tickets_offers[0].time_range)

                        binding.tvNameBlue.text = it.tickets_offers[1].title
                        binding.tvPrice2.text =
                            Converters().convertPrice(it.tickets_offers[1].price.value)
                        binding.tvTime2.text =
                            Converters().convertTime(it.tickets_offers[1].time_range)

                        binding.tvNameWhite.text = it.tickets_offers[2].title
                        binding.tvPrice3.text =
                            Converters().convertPrice(it.tickets_offers[2].price.value)
                        binding.tvTime3.text =
                            Converters().convertTime(it.tickets_offers[2].time_range)
                    }
                }

            }
        }

    }

    private fun setDate() {
        val calendar = Calendar.getInstance()
        var date = "${calendar.get(Calendar.DAY_OF_MONTH)} ${calendar.get(Calendar.MONTH)}, ${
            calendar.get(Calendar.DAY_OF_WEEK)
        }"
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        Log.d("LOL", "setDate работает")
        val datePickerDialog = DatePickerDialog(
            requireContext(),
        )
        datePickerDialog.show()

    }

}