package com.artem.weatherappgeekbrains.model

interface Repository {
    fun getWeatherFromServer(): MutableList<City>
    fun getWeatherFromLocalStorageRus(): MutableList<City>
    fun getWeatherFromLocalStorageWorld(): MutableList<City>
}