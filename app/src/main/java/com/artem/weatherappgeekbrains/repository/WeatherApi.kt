package com.artem.weatherappgeekbrains.repository

import com.artem.weatherappgeekbrains.model.WeatherDTO
import com.artem.weatherappgeekbrains.utils.API_KEY
import com.artem.weatherappgeekbrains.utils.WEATHER_API_BASE_URL_END_POINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherApi {
    @GET(WEATHER_API_BASE_URL_END_POINT)
    fun getWeather(
        @Header(API_KEY) apikey: String,
        @Query("q") cityName: String
    ): Call<WeatherDTO>
}