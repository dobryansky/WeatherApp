package com.artem.weatherappgeekbrains.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class City(
    val name: String,
    val weatherDTO:WeatherDTO?=null,
    val image: String = "https://placeimg.com/200/200/arch"
) : Parcelable



fun getWorldCities(): MutableList<City> {
    return mutableListOf(
        City("Лондон" ),
        City("Токио" ),
        City("Париж"),
        City("Берлин" ),
        City("Рим" ),
        City("Минск" ),
        City("Стамбул" ),
        City("Вашингтон" ),
        City("Киев" ),
        City("Пекин")
    )
}


fun getRussianCities(): MutableList<City>{
    return mutableListOf(
      City("Moscow"),
        City("Saint-Petersburg"),
        City("Novosibirsk"),
        City("Yekaterinburg"),
        City("Nizhny novgorod"),
        City("Kazan"),
        City("Chelyabinsk"),
        City("Omsk"),
        City("Rostov-on-Don"),
        City("Ufa"),
    )
}
