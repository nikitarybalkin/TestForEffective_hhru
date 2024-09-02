package com.example.aviaapp.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aviaapp.R
import com.example.aviapp.data.ButtonFunction
import com.example.aviaapp.databinding.ItemButtonBinding
import com.example.aviaapp.databinding.ItemMusicallyBinding

class ButtonsAdapter(val context: Context, val buttonsActions: List<com.example.aviapp.data.ButtonFunction>): RecyclerView.Adapter<ButtonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val binding = ItemButtonBinding.inflate(LayoutInflater.from(parent.context))
        return ButtonViewHolder(binding)
    }

    override fun getItemCount(): Int = buttonsActions.size

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        if (buttonsActions[position].url != null) {
            holder.binding.iv.visibility = View.VISIBLE
            Glide
                .with(context)
                .load(buttonsActions[position].url)
                .into(holder.binding.iv)
            holder.binding.iv
        }
        holder.binding.button.text = buttonsActions[position].name
        holder.binding.button.setOnClickListener {
            buttonsActions[position].action()
            Log.d("LOL","на кнопку тапнули")
        }
    }

}
class ButtonViewHolder(val binding: ItemButtonBinding): RecyclerView.ViewHolder(binding.root)