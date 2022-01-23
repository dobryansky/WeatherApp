package com.artem.weatherappgeekbrains.model

object CityList {
    var citiesRussian:MutableList<City> = getRussianCities()
    var citiesWorld:MutableList<City> = getWorldCities()
}