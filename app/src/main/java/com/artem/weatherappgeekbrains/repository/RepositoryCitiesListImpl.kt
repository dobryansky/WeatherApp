package com.artem.weatherappgeekbrains.repository

import com.artem.weatherappgeekbrains.BuildConfig
import com.artem.weatherappgeekbrains.model.City
import com.artem.weatherappgeekbrains.model.CityList
import com.artem.weatherappgeekbrains.model.WeatherDTO
import com.artem.weatherappgeekbrains.utils.WEATHER_API_BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryCitiesListImpl: RepositoryCitiesList,RepositoryDetails {


    override fun getWeatherFromLocalStorageRus(): MutableList<City> {
        return CityList.citiesRussian
    }

    override fun getWeatherFromLocalStorageWorld(): MutableList<City> {
        return CityList.citiesWorld
    }

    override fun getWeatherFromServer(cityName: String, callback: Callback<WeatherDTO>) {
        val retrofit = Retrofit.Builder()
            .baseUrl(WEATHER_API_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build().create(WeatherApi::class.java)
        retrofit.getWeather(BuildConfig.WEATHER_API_KEY,cityName).enqueue(callback)
    }


}