package com.artem.weatherappgeekbrains.model

object CityList {
    var citiesRussian:MutableList<Weather> = getRussianCities().toMutableList()
    var citiesWorld:MutableList<Weather> = getWorldCities().toMutableList()
}