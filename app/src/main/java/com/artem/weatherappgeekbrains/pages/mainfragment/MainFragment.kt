package com.artem.weatherappgeekbrains.pages.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.artem.weatherappgeekbrains.R
import com.artem.weatherappgeekbrains.adapters.MainFragmentAdapter
import com.artem.weatherappgeekbrains.adapters.OnMyItemClickListener
import com.artem.weatherappgeekbrains.databinding.MainFragmentBinding
import com.artem.weatherappgeekbrains.model.AppState
import com.artem.weatherappgeekbrains.model.CityList
import com.artem.weatherappgeekbrains.model.Weather
import com.artem.weatherappgeekbrains.pages.detailsfragment.BUNDLE_KEY
import com.artem.weatherappgeekbrains.pages.detailsfragment.DetailsFragment
import com.artem.weatherappgeekbrains.pages.dialogfragments.AddCityFragment
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(), OnMyItemClickListener {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainFragmentViewModel
    private var isRussian = true
    private var adapter = MainFragmentAdapter(this, isRussian)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        viewModel.getWeatherFromLocalSourceRus()
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })

        adapter.setWeather(CityList.citiesWorld)

        with(binding){
            mainRecycleView.layoutManager = LinearLayoutManager(context)
            mainRecycleView.adapter = adapter
            buttonAdd.setOnClickListener {
                val dialogFragment = AddCityFragment()
                val args = Bundle()
                args.putBoolean("isRussian", isRussian)
                dialogFragment.arguments = args
                dialogFragment.show(parentFragmentManager, "Add Dialog")
            }
            mainFragmentFAB.setOnClickListener {
                sentRequest()
            }
        }


    }


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.progressBar.visibility = View.GONE
               /* Snackbar.make(binding.root, "Error", Snackbar.LENGTH_LONG)
                    .setAction("Попробовать еще раз") {
                        sentRequest()
                    }.show()*/
            }
            is AppState.Loading -> {
                binding.apply {
                    progressBar.visibility = View.VISIBLE
                    mainLayoutList.alpha = 0.8f
                    mainLayoutList.setBackgroundResource(R.color.purple_700)
                }
            }
            is AppState.Success -> {
                binding.apply {
                    progressBar.visibility = View.GONE
                    mainLayoutList.alpha = 1f
                    mainLayoutList.setBackgroundResource(R.color.white)
                }
                adapter.setWeather(appState.weatherData)
                /*Snackbar.make(
                    binding.root,
                    "Success",
                    Snackbar.LENGTH_LONG
                ).show()*/
            }
        }
    }

    private fun sentRequest() {
        isRussian = !isRussian
        adapter = MainFragmentAdapter(this, isRussian)
        with(binding){
            mainRecycleView.layoutManager = LinearLayoutManager(context)
            mainRecycleView.adapter = adapter

            if (isRussian) {
                viewModel.getWeatherFromLocalSourceRus()
                mainFragmentFAB.setImageResource(R.drawable.ic_russia)
                adapter.setWeather(CityList.citiesRussian)

            } else {
                viewModel.getWeatherFromLocalSourceWorld()
                mainFragmentFAB.setImageResource(R.drawable.ic_earth)
                adapter.setWeather(CityList.citiesWorld)
            }
        }


    }


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onItemClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(BUNDLE_KEY, weather)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, DetailsFragment.newInstance(bundle))
            .addToBackStack("").commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}