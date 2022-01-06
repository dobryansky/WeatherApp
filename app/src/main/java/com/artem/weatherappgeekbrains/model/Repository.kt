package com.artem.weatherappgeekbrains.model

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): MutableList<Weather>
    fun getWeatherFromLocalStorageWorld(): MutableList<Weather>
}