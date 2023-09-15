package com.tasha.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tasha.data.local.entity.Vehicle
import com.tasha.databinding.ActivitySecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity: AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding
    lateinit var vehicle: Vehicle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let{
            vehicle = it.getParcelable<Vehicle>("vehicle")!!
        }

        binding.textView.text = vehicle.name
    }
}