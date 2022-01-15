package com.artem.weatherappgeekbrains.pages.detailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.artem.weatherappgeekbrains.databinding.DetailsFragmentBinding
import com.artem.weatherappgeekbrains.extensions.loadImageFromUrl
import com.artem.weatherappgeekbrains.model.City

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
        val weather = arguments?.getParcelable<City>(BUNDLE_KEY)
        weather?.let {
            setWeatherData(weather)
        }
    }

    private fun setWeatherData(city: City) {
        with(binding){
            imageCity.loadImageFromUrl(city.image)
            cityName.text = city.name
            temperatureValue.text = city.weatherDTO?.current?.temp_c.toString()
            feelsLikeValue.text = city.weatherDTO?.current?.feelslike_c.toString()
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