package com.artem.weatherappgeekbrains.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar


private fun View.withAction(
    text: String,
    actionText: Int,
    action: () -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length)
        .setAction(actionText) {
            action
        }.show()
}