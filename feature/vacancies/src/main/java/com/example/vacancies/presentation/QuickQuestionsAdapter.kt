package com.example.vacancies.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vacancies.databinding.ItemQuickQuestionsBinding

class QuickQuestionsAdapter(
    private val questions: List<String>,
    private val goToSendPage: (String?) -> Unit
):  RecyclerView.Adapter<QuickQuestionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickQuestionsViewHolder {
        val binding =
            ItemQuickQuestionsBinding.inflate(LayoutInflater.from(parent.context))
        return QuickQuestionsViewHolder(binding)
    }

    override fun getItemCount() = questions.size

    override fun onBindViewHolder(holder: QuickQuestionsViewHolder, position: Int) {
        holder.binding.b.text = questions[position]
        holder.binding.b.setOnClickListener {
            goToSendPage(questions[position])
        }
    }
}

class QuickQuestionsViewHolder(val binding: ItemQuickQuestionsBinding) : RecyclerView.ViewHolder(binding.root)