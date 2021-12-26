package com.artem.weatherappgeekbrains.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artem.weatherappgeekbrains.databinding.ItemBinding
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
            cityTemperature.text = weatherItem.temperature.toString()
            feelsLike.text = weatherItem.feelsLike.toString()
        }
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    inner class Viewholder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
}