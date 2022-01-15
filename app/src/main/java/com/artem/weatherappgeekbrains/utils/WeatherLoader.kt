package com.artem.weatherappgeekbrains.utils

import android.os.Handler
import android.os.Looper
import com.artem.weatherappgeekbrains.model.WeatherDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


class WeatherLoader(private val onWeatherLoaded: OnWeatherLoaded) {

    fun loadWeather(lat: Double, lon: Double) {

        /*try {
 // работаем здесь
        }catch (e:Throwable){
            // выводим ошибки
            onWeatherLoaded.onFailed()
        } finally {
            httpsURLConnection.disconnect()
        }*/

        Thread {
            val url = URL("https://api.weatherapi.com/v1/current.json?key=11203b938d0d408385d134411212211&q=Moscow")
            val httpsURLConnection = (url.openConnection() as HttpsURLConnection).apply {
                requestMethod = "GET"
                readTimeout = 2000

                //addRequestProperty("key", "11203b938d0d408385d134411212211")
            }
            val bufferedReader = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
            val weatherDTO: WeatherDTO? =
                Gson().fromJson(convertBufferToResult(bufferedReader), WeatherDTO::class.java)
            Handler(Looper.getMainLooper()).post {
               onWeatherLoaded.onLoaded(weatherDTO)
            }
        }.start()

    }

    private fun convertBufferToResult(bufferedReader: BufferedReader): String {
        return bufferedReader.lines().collect(Collectors.joining("\n"))
    }

    interface OnWeatherLoaded {
        fun onLoaded(weatherDTO: WeatherDTO?)
        fun onFailed() // TODO HW
    }
}