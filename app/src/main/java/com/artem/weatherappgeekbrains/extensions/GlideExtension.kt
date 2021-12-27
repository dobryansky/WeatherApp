package com.artem.weatherappgeekbrains.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey


fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this)
        .load(url)
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .signature(ObjectKey(System.currentTimeMillis().toString()))
        .into(this)
}
