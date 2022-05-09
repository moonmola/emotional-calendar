package com.moonmola.emotional_calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.moonmola.emotional_calendar.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}