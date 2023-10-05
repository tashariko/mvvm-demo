package com.tasha.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tasha.core.HomeScreenType
import com.tasha.data.local.entity.ParentEntity
import com.tasha.data.local.entity.People
import com.tasha.data.local.entity.Planet
import com.tasha.data.local.entity.Vehicle
import com.tasha.databinding.ActivitySecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity: AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding
    private lateinit var mainEntity: ParentEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let{
            when(it.getString("type")){
                HomeScreenType.Vehicle.name -> {
                    mainEntity = it.getParcelable<Vehicle>("vehicle")!!
                }
                HomeScreenType.People.name -> {
                    mainEntity = it.getParcelable<Planet>("people")!!
                }
                HomeScreenType.Planet.name -> {
                    mainEntity = it.getParcelable<People>("planet")!!
                }
            }
        }

        when(mainEntity){
            is People -> {
                with(mainEntity as People){
                    binding.textView.text = name
                }
            }
            is Planet -> {
                with(mainEntity as Planet){
                    binding.textView.text = name
                }
            }
            is Vehicle -> {
                with(mainEntity as Vehicle){
                    binding.textView.text = name
                }
            }
            else -> {

            }
        }
    }
}