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

    fun loadWeather(name: String) {

        /*try {
 // работаем здесь
        }catch (e:Throwable){
            // выводим ошибки
            onWeatherLoaded.onFailed()
        } finally {
            httpsURLConnection.disconnect()
        }*/

        Thread {
            try {
                val url =
                    URL("https://api.weatherapi.com/v1/current.json?key=11203b938d0d408385d134411212211&q=$name")
                val httpsURLConnection = (url.openConnection() as HttpsURLConnection).apply {
                    requestMethod = "GET"
                    readTimeout = 1000
                    //addRequestProperty("key", "11203b938d0d408385d134411212211")
                }
                val bufferedReader =
                    BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                var weatherDTO: WeatherDTO? =
                    Gson().fromJson(convertBufferToResult(bufferedReader), WeatherDTO::class.java)
                Handler(Looper.getMainLooper()).post {
                    onWeatherLoaded.onLoaded(weatherDTO)
                }
            } catch (e: Throwable) {

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