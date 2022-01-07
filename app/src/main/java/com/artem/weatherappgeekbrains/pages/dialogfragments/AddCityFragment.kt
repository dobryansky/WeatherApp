package com.artem.weatherappgeekbrains.pages.dialogfragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.artem.weatherappgeekbrains.R
import com.artem.weatherappgeekbrains.model.City
import com.artem.weatherappgeekbrains.model.CityList
import com.artem.weatherappgeekbrains.model.Weather
import com.google.android.material.textfield.TextInputEditText

class AddCityFragment : DialogFragment() {

    private var cityName: TextInputEditText? = null
    private var longitude: TextInputEditText? = null
    private var latitude: TextInputEditText? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_fragment, null)
        val isRussian=arguments?.getBoolean("isRussian")
        cityName = view.findViewById(R.id.city_name_dialog)
        longitude = view.findViewById(R.id.lon_txt)
        latitude = view.findViewById(R.id.lan_txt)
        builder.setView(view)
            .setTitle("Введите координаты города")
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->

                try {
                    val newCityName:String = cityName?.text.toString()
                    val newCityLon: Double = longitude?.text.toString().toDouble()
                    val newCityLat: Double = latitude?.text.toString().toDouble()
                    val newWeather=Weather(City(newCityName,newCityLon,newCityLat,"https://placeimg.com/200/200/arch"),666,777)
                    if(isRussian == true) {
                        CityList.citiesRussian.add(newWeather)
                    }else{
                        CityList.citiesWorld.add(newWeather)
                    }
                } catch (e:Exception){
                    Toast.makeText(context,"введите поля!!!",Toast.LENGTH_SHORT).show()
                }

            })
        return builder.create()
    }

}