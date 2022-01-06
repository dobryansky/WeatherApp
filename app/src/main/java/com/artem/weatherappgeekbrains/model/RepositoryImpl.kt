package com.artem.weatherappgeekbrains.model

class RepositoryImpl:Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorageRus(): MutableList<Weather> {
        return CityList.citiesRussian
    }

    override fun getWeatherFromLocalStorageWorld(): MutableList<Weather> {
        return CityList.citiesWorld
    }


}