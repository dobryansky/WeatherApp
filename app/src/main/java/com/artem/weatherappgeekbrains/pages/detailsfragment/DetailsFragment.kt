package com.artem.weatherappgeekbrains.pages.detailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.artem.weatherappgeekbrains.databinding.DetailsFragmentBinding
import com.artem.weatherappgeekbrains.extensions.loadImageFromUrl
import com.artem.weatherappgeekbrains.model.AppState
import com.artem.weatherappgeekbrains.model.City
import com.artem.weatherappgeekbrains.model.CityList
import com.artem.weatherappgeekbrains.model.WeatherDTO
import com.artem.weatherappgeekbrains.utils.BUNDLE_KEY
import com.artem.weatherappgeekbrains.utils.WeatherLoader


class DetailsFragment : Fragment(), WeatherLoader.OnWeatherLoaded {
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    var position =0

    private val mViewModel: DetailsFragmentViewModel by lazy {
        ViewModelProvider(this)[DetailsFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    lateinit var city: City
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getLiveData().observe(viewLifecycleOwner,{
            renderData(it)
        })
        arguments?.let {
            it.getParcelable<City>(BUNDLE_KEY)?.let {
                city = it
                mViewModel.getWeatherFromRemoteServer(city.name)
            }
        }
    }

    private fun renderData(appState: AppState) {
        with(binding) {
            when (appState) {
                is AppState.Error -> {
                    // HW
                }
                is AppState.Loading -> {
                    // HW
                }
                is AppState.Success -> {
                    val weather = appState.cityList[0]
                    setCityWeather(weather)
                }
            }
        }
    }


    private fun setCityWeather(city: City) {
        with(binding) {
            imageCity.loadImageFromUrl(city.image)
            cityName.text = city.name
            temperatureValue.text = city.weatherDTO?.current?.temp_c.toString()
            feelsLikeValue.text = city.weatherDTO?.current?.feelslike_c.toString()
        }
    }

    companion object {
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLoaded(weatherDTO: WeatherDTO?) {
        val city = arguments?.getParcelable<City>(BUNDLE_KEY)
        if (city != null) {
            setCityWeather(City(city.name, weatherDTO))
        }

    }

    override fun onFailed() {
        TODO("Not yet implemented")
    }

}