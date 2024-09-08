package com.example.vacancies.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.utils.Converters
import com.example.network.data.Vocation
import com.example.vacancies.R
import com.example.vacancies.databinding.ItemVacancyBinding

class VacancyAdapter(
    private val vocations: List<Vocation>,
    private val goToFullVacancyPage: (number: Int) -> Unit,
    private val addToFavorites: (Vocation) -> Unit
) :
    RecyclerView.Adapter<VacancyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val binding = ItemVacancyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyViewHolder(binding)
    }

    override fun getItemCount() = vocations.size

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        holder.binding.let {
            if (vocations[position].lookingNumber != null) {
                it.tvNowWatching.text = it.root.context.resources.getQuantityString(
                    com.example.core.R.plurals.currently_viewing,
                    vocations[position].lookingNumber,
                    vocations[position].lookingNumber
                )
            }
            if (vocations[position].isFavorite) {
                it.icHeart.setImageResource(com.example.core.R.drawable.ic_heart_activated)
            }
            it.tvVocation.text = vocations[position].title
            it.tvTown.text = vocations[position].address.town
            it.tvCompany.text = vocations[position].company
            it.tvExperience.text = vocations[position].experience.previewText
            val date = Converters().formatDate(vocations[position].publishedDate)
            it.tvDateOfPublication.text = it.root.context.getString(
                com.example.core.R.string.publicated_date, date.first,
                date.second
            )
            it.bRespond.setOnClickListener {
                goToFullVacancyPage(position)
            }
            it.icHeart.setOnClickListener {
                addToFavorites(vocations[position])
                holder.binding.icHeart.setImageResource(com.example.core.R.drawable.ic_heart_activated)
            }
        }
    }
}

class VacancyViewHolder(val binding: ItemVacancyBinding) : RecyclerView.ViewHolder(binding.root)