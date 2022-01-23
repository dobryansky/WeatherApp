package com.artem.weatherappgeekbrains.pages.detailsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artem.weatherappgeekbrains.model.AppState
import com.artem.weatherappgeekbrains.model.City
import com.artem.weatherappgeekbrains.model.CityList
import com.artem.weatherappgeekbrains.model.WeatherDTO
import com.artem.weatherappgeekbrains.repository.RepositoryCitiesListImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragmentViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData()
) : ViewModel() {

    private val repositoryImpl: RepositoryCitiesListImpl by lazy {
        RepositoryCitiesListImpl()
    }
    fun getLiveData() = liveData
    fun getWeatherFromRemoteServer(cityName:String) {
        liveData.postValue(AppState.Loading(0))
        repositoryImpl.getWeatherFromServer(cityName ,callback)
    }
    private val callback = object : Callback<WeatherDTO> {
        override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
            if(response.isSuccessful){
                response.body()?.let {
                    liveData.postValue(AppState.Success(
                        mutableListOf(City(it.location.name,it,"https://placeimg.com/200/200/arch"))
                    ))
                }
            }else{
                // TODO HW
            }
        }
        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
            // HW TODO("Not yet implemented")
        }
    }

}