package com.artem.weatherappgeekbrains.pages.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.artem.weatherappgeekbrains.adapters.MainFragmentAdapter
import com.artem.weatherappgeekbrains.adapters.OnMyItemClickListener
import com.artem.weatherappgeekbrains.databinding.MainFragmentBinding
import com.artem.weatherappgeekbrains.model.Repository
import com.artem.weatherappgeekbrains.model.Weather
import com.artem.weatherappgeekbrains.model.getWorldCities

class MainFragment : Fragment(),OnMyItemClickListener {
    private var _binding: MainFragmentBinding? = null
       private val binding get() = _binding!!
    private val adapter = MainFragmentAdapter(this)



    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setWeather(getWorldCities())
        binding.mainRecycleView.layoutManager= LinearLayoutManager(context)
        binding.mainRecycleView.adapter=adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onItemClick(weather: Weather) {
        TODO("Not yet implemented")
    }


}