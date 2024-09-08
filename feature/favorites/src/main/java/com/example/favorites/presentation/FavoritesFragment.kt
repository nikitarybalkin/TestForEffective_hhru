package com.example.favorites.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.di.ViewModelFactory
import com.example.favorites.databinding.FragmentFavoritesBinding
import com.example.favorites.di.FavoritesComponent
import com.example.favorites.di.FavoritesComponentProvider
import com.example.favorites.utils.Converter
import com.example.vacancies.presentation.VacancyAdapter
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class FavoritesFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var favoritesComponent: FavoritesComponent
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        favoritesComponent =
            (requireActivity().applicationContext as FavoritesComponentProvider).provideFavoritesComponent()
        favoritesComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoritesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.vacancies.collect {
                if (it != null && it.isNotEmpty()) {
                    binding.tvNumberVacancies.text = it.size.toString()
                    binding.rvFavoriteVacancies.adapter = VacancyAdapter(Converter().convertFromLocalDataToModel(it), {}, {})
                }
            }
        }
    }
}