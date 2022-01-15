package com.artem.weatherappgeekbrains.model



sealed class AppState {
    data class Loading(val progress:Int):AppState()
    data class Success(val cityList:MutableList<City>):AppState()
    data class Error( val error:Throwable):AppState()
}