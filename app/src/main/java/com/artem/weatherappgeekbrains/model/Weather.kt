package com.artem.weatherappgeekbrains.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class City(
    val name: String,
    var weatherDTO:WeatherDTO?=null,
    val image: String = "https://placeimg.com/200/200/arch"
) : Parcelable



fun getWorldCities(): MutableList<City> {
    return mutableListOf(
        City("London" ),
        City("Tokyo" ),
        City("Paris"),
        City("Berlin" ),
        City("Rome" ),
        City("Minsk" ),
        City("Istanbul" ),
        City("Washington" ),
        City("Kiev" ),
        City("Beijing")
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
