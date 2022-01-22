package com.artem.weatherappgeekbrains.pages.dialogfragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.artem.weatherappgeekbrains.R
import com.artem.weatherappgeekbrains.databinding.DialogFragmentBinding
import com.artem.weatherappgeekbrains.model.City
import com.artem.weatherappgeekbrains.model.CityList
import com.google.android.material.textfield.TextInputEditText

class AddCityFragment(val callback: IDialogCallback) : DialogFragment() {


    private lateinit var binding: DialogFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initButtonListeners()
    }

    private fun initButtonListeners() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnOk.setOnClickListener {
            val isRussian = arguments?.getBoolean("isRussian")
            val newCityName: String = binding.cityNameDialog.text.toString()
            val newCity = City(newCityName, null)
            if (isRussian == true) {
                CityList.citiesRussian.add(0, newCity)
            } else {
                CityList.citiesWorld.add(0, newCity)
            }
            callback.updateRecyclerView()
            dismiss()
        }

    }


   /* private var cityName: TextInputEditText? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_fragment, null)
        val isRussian = arguments?.getBoolean("isRussian")

        cityName = view.findViewById(R.id.city_name_dialog)
        builder.setView(view)
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->

                try {
                    val newCityName: String = cityName?.text.toString()
                    val newCity = City(newCityName, null)
                    if (isRussian == true) {
                        CityList.citiesRussian.add(0, newCity)
                    } else {
                        CityList.citiesWorld.add(0, newCity)
                    }
                    callback.updateRecyclerView()




                } catch (e: Exception) {
                    Toast.makeText(context, "введите поля!!!", Toast.LENGTH_SHORT).show()
                }

            })
        return builder.create()
    }*/

    interface IDialogCallback {
        fun updateRecyclerView()
    }


}



