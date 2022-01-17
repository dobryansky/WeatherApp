package com.artem.weatherappgeekbrains.pages.mainfragment

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()

) : ViewModel() {

    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun getWeatherFromLocalSourceRus() = getWeatherFromLocalServer(true)

    fun getWeatherFromLocalSourceWorld() = getWeatherFromLocalServer(false)

    fun getWeatherFromRemoteSourceRus() = getWeatherFromRemoteServer(true)
    fun getWeatherFromRemoteSourceWorld() = getWeatherFromRemoteServer(true)


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
                    URL("https://api.weatherapi.com/v1/current.json?key=11203b938d0d408385d134411212211&q=${weatherItem.name}")
                val httpsURLConnection = (url.openConnection() as HttpsURLConnection).apply {
                    requestMethod = "GET"
                    //readTimeout = 500
                    //addRequestProperty("key", "11203b938d0d408385d134411212211")
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