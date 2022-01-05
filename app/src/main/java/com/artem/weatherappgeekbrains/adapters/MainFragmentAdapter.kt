package com.artem.weatherappgeekbrains.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artem.weatherappgeekbrains.databinding.ItemBinding
import com.artem.weatherappgeekbrains.extensions.loadImageFromUrl
import com.artem.weatherappgeekbrains.model.Weather

class MainFragmentAdapter(val listener: OnMyItemClickListener) :
    RecyclerView.Adapter<MainFragmentAdapter.Viewholder>() {
    private var weatherData: List<Weather> = listOf()


    fun setWeather(data: List<Weather>) {
        this.weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val weatherItem = weatherData[position]
        with(holder.binding) {
            cityName.text = weatherItem.city.name
            cityTemperature.text ="${weatherItem.temperature}°"
            feelsLike.text ="feels like ${weatherItem.feelsLike}°"
            imageView.loadImageFromUrl(weatherItem.city.image)
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(weatherItem)
        }

    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

     class Viewholder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){

    }
}