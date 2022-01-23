package com.artem.weatherappgeekbrains.repository

import com.artem.weatherappgeekbrains.model.WeatherDTO

interface RepositoryDetails {
    fun getWeatherFromServer(cityName:String,callback: retrofit2.Callback<WeatherDTO>)
}