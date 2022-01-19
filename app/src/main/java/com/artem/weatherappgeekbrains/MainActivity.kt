package com.artem.weatherappgeekbrains

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artem.weatherappgeekbrains.pages.mainfragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance()).commit()
        }
    }
}


//https://source.unsplash.com/random/800x600?{moscow}