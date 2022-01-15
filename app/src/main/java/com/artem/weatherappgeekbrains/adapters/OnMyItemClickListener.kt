package com.artem.weatherappgeekbrains.adapters
import com.artem.weatherappgeekbrains.model.City


interface OnMyItemClickListener {
    fun onItemClick(city: City)
}