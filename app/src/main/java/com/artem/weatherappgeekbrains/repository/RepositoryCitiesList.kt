package com.artem.weatherappgeekbrains.repository

import com.artem.weatherappgeekbrains.model.City

interface RepositoryCitiesList {
    fun getWeatherFromLocalStorageRus(): MutableList<City>
    fun getWeatherFromLocalStorageWorld(): MutableList<City>
}