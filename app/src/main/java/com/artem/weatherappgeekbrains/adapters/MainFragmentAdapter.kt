package com.artem.weatherappgeekbrains.adapters

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artem.weatherappgeekbrains.R
import com.artem.weatherappgeekbrains.databinding.ItemBinding
import com.artem.weatherappgeekbrains.extensions.loadIconFromUrl
import com.artem.weatherappgeekbrains.extensions.loadImageFromUrl
import com.artem.weatherappgeekbrains.model.City
import com.artem.weatherappgeekbrains.model.WeatherDTO
import com.artem.weatherappgeekbrains.utils.WeatherLoader
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainFragmentAdapter(val listener: OnMyItemClickListener, val isRussian: Boolean) :
    RecyclerView.Adapter<MainFragmentAdapter.Viewholder>(), WeatherLoader.OnWeatherLoaded {
    private var weatherData: MutableList<City> = mutableListOf()
    var cityPosition = 0
    fun setWeather(data: MutableList<City>) {
        this.weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    var weatherDTO: WeatherDTO? = null

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val weatherItem = weatherData[position]

    with(holder.binding) {
        cityName.text = weatherItem.name
        cityTemperature.text = "${weatherItem.weatherDTO?.current?.temp_c}°"
        feelsLike.text = "feels like ${weatherItem.weatherDTO?.current?.feelslike_c}°"
        country.text=weatherItem.weatherDTO?.location?.country
        windDirPressure.text="${weatherItem.weatherDTO?.current?.wind_kph} km/h, dir - ${weatherItem.weatherDTO?.current?.wind_dir}"
        imageView.loadImageFromUrl(weatherItem.image)
        imgIcon.loadIconFromUrl(weatherItem.weatherDTO?.current?.condition?.icon)
        if (isRussian) {
            itemColorLayout.setBackgroundResource(R.drawable.gradient_background_item)

        } else {
            itemColorLayout.setBackgroundResource(R.drawable.gradient_background_world_item)
        }


    }

        holder.itemView.setOnClickListener {
            listener.onItemClick(weatherItem)
        }

    }

    private fun convertBufferToResult(bufferedReader: BufferedReader): String {
        return bufferedReader.lines().collect(Collectors.joining("\n"))
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    class Viewholder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onLoaded(weatherDTO: WeatherDTO?) {
        // weatherData[cityPosition].weatherDTO = weatherDTO
    }

    override fun onFailed() {
    }
}