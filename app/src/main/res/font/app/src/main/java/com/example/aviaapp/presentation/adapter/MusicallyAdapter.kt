package com.example.aviaapp.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.aviaapp.R
import com.example.aviaapp.databinding.ItemMusicallyBinding
import com.example.aviaapp.domain.apiModel.model.Offer
import com.example.aviaapp.domain.model.OffersModel
import com.example.aviaapp.utils.Converters
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MusicallyAdapter(
    private val offer: List<Offer>,
    private val context: Context,
    private val images: List<Int>
): Adapter<MusicallyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicallyViewHolder {
        val binding = ItemMusicallyBinding.inflate(LayoutInflater.from(parent.context))
        return MusicallyViewHolder(binding)
    }

    override fun getItemCount(): Int = offer.size

    override fun onBindViewHolder(holder: MusicallyViewHolder, position: Int) {
        Glide
            .with(context)
            .load(images[position])
            .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(16, 0)))
            .into(holder.binding.ivMusically)
        holder.binding.let {
            it.tvCity.text = offer[position].town
            it.tvName.text = offer[position].title
            it.tvPrice.text = Converters().convertPrice(offer[position].price.value)
        }
    }
}

class MusicallyViewHolder(val binding: ItemMusicallyBinding): RecyclerView.ViewHolder(binding.root)