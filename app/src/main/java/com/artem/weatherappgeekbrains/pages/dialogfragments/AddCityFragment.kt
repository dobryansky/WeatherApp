package com.artem.weatherappgeekbrains.pages.dialogfragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.artem.weatherappgeekbrains.R

class AddCityFragment:DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder:AlertDialog.Builder= AlertDialog.Builder(requireContext())
        val view= layoutInflater.inflate(R.layout.dialog_fragment,null)
        builder.setView(view)
            .setTitle("Введите координаты")
            .setPositiveButton("Ok",DialogInterface.OnClickListener { dialogInterface, i ->  })

        return builder.create()
    }
}