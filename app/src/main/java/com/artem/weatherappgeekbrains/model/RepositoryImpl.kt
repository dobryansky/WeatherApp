package com.artem.weatherappgeekbrains.model

class RepositoryImpl:Repository {
    override fun getWeatherFromServer(): MutableList<City> {
        return  CityList.citiesRussian
    }

    override fun getWeatherFromLocalStorageRus(): MutableList<City> {
        return CityList.citiesRussian
    }

    override fun getWeatherFromLocalStorageWorld(): MutableList<City> {
        return CityList.citiesWorld
    }


}