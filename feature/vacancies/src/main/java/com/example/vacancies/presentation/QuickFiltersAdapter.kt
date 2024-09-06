package com.example.vacancies.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.data.Offer
import com.example.vacancies.databinding.ItemQuickFiltersBinding

class QuickFiltersAdapter(private val offers: List<Offer>): RecyclerView.Adapter<QuickFiltersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickFiltersViewHolder {
        val binding = ItemQuickFiltersBinding.inflate(LayoutInflater.from(parent.context))
        return QuickFiltersViewHolder(binding)
    }

    override fun getItemCount() = offers.lastIndex

    override fun onBindViewHolder(holder: QuickFiltersViewHolder, position: Int) {
        holder.binding.let {
            when (offers[position].id) {
                "near_vacancies" -> it.ic.setImageResource(com.example.core.R.drawable.ic_near_vacancies)
                "temporary_job" -> it.ic.setImageResource(com.example.core.R.drawable.ic_temporary_job)
                "level_up_resume" -> it.ic.setImageResource(com.example.core.R.drawable.ic_level_up_resume)
            }
            it.tv.setText(offers[position].title)
            if (offers[position].button != null) {
                it.tvB.visibility = View.VISIBLE
                it.tvB.setText(offers[position].button.text)
            }
        }

    }
}

class QuickFiltersViewHolder(val binding: ItemQuickFiltersBinding): RecyclerView.ViewHolder(binding.root)