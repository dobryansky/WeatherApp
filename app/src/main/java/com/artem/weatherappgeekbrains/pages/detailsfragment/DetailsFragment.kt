package com.artem.weatherappgeekbrains.pages.detailsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artem.weatherappgeekbrains.databinding.DetailsFragmentBinding
import com.artem.weatherappgeekbrains.model.Weather

const val BUNDLE_KEY = "key"
class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var mViewModel: DetailsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = arguments?.getParcelable<Weather>(BUNDLE_KEY)
        if(weather!=null){
            setWeatherData(weather)
        }
    }

    private fun setWeatherData(weather: Weather) {
        with(binding){
            cityName.text = weather.city.name
            cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"
            temperatureValue.text = "${weather.temperature}"
            feelsLikeValue.text = "${weather.feelsLike}"
        }

    }

    companion object {
        fun newInstance(bundle:Bundle):DetailsFragment {
            val fragment  = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}