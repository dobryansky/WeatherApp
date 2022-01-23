package com.artem.weatherappgeekbrains.pages.mainfragment

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artem.weatherappgeekbrains.BuildConfig
import com.artem.weatherappgeekbrains.model.AppState
import com.artem.weatherappgeekbrains.model.RepositoryImpl
import com.artem.weatherappgeekbrains.model.WeatherDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainFragmentViewModel(
     val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()

) : ViewModel() {

    fun getLiveData(): LiveData<AppState> {
        return liveData    }

    fun getWeatherFromRemoteSourceRus() = getWeatherFromRemoteServer(true)
    fun getWeatherFromRemoteSourceWorld() = getWeatherFromRemoteServer(false)


    private fun getWeatherFromLocalServer(isRussian: Boolean) {
        liveData.postValue(AppState.Loading(0))
        Thread {
            Thread.sleep(1000)
            liveData.postValue(
                AppState.Success(
                    if (isRussian) {
                        repositoryImpl.getWeatherFromLocalStorageRus()
                    } else {
                        repositoryImpl.getWeatherFromLocalStorageWorld()
                    }
                )
            )
        }.start()
    }

    fun getWeatherFromRemoteServer(isRussian: Boolean) {

        val weatherList= if (isRussian) {
            repositoryImpl.getWeatherFromLocalStorageRus()
        } else {
            repositoryImpl.getWeatherFromLocalStorageWorld()
        }
        weatherList.forEach {weatherItem->
            Thread {
                val url =
                    URL("https://api.weatherapi.com/v1/current.json?q=${weatherItem.name}")
                val httpsURLConnection = (url.openConnection() as HttpsURLConnection).apply {
                    requestMethod = "GET"
                    //readTimeout = 500
                    addRequestProperty("key", BuildConfig.WEATHER_API_KEY)
                }
                val bufferedReader = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val weatherDTO =
                    Gson().fromJson(convertBufferToResult(bufferedReader), WeatherDTO::class.java)

                weatherItem.weatherDTO = weatherDTO
                liveData.postValue(AppState.Success(weatherList))

            }.start()
        }
    }

    private fun convertBufferToResult(bufferedReader: BufferedReader): String {
        return bufferedReader.lines().collect(Collectors.joining("\n"))
    }
}