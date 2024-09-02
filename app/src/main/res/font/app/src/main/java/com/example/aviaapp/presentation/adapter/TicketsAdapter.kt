package com.example.aviaapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aviaapp.databinding.ItemMusicallyBinding
import com.example.aviaapp.databinding.ItemTicketBinding
import com.example.aviaapp.domain.apiModel.model.Ticket
import com.example.aviaapp.utils.Converters

class TicketsAdapter(private var tickets: List<Ticket>): RecyclerView.Adapter<TicketsViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val binding = ItemTicketBinding.inflate(LayoutInflater.from(parent.context))
        return TicketsViewHolder(binding)
    }

    override fun getItemCount(): Int = tickets.size

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        holder.binding.let {
        if (tickets[position].badge != "") {
            it.shield.visibility = View.VISIBLE
            it.shield.text = tickets[position].badge
            }
        it.tvPrice.text = Converters().convertPrice(tickets[position].price.value)
        it.tvAirport1.text = tickets[position].departure.airport
        it.tvAirport2.text = tickets[position].arrival.airport
        it.tvFullTime.text = Converters()
            .getFullTime(tickets[position].departure.date, tickets[position].arrival.date)
        }
    }
}

class TicketsViewHolder(val binding: ItemTicketBinding): RecyclerView.ViewHolder(binding.root)