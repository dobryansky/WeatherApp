package com.artem.weatherappgeekbrains.adapters
import com.artem.weatherappgeekbrains.model.Weather


interface OnMyItemClickListener {
    fun onItemClick(weather: Weather)
}