package com.tasha.screens.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasha.EspressoIdlingResource
import com.tasha.customviews.FullScreenViewType
import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Vehicle
import com.tasha.databinding.LayoutVehicleBinding
import com.tasha.screens.SecondActivity
import com.tasha.screens.data.VehicleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleActivity :AppCompatActivity() {

    val viewModel: VehicleViewModel by viewModels()
    lateinit var binding: LayoutVehicleBinding

    val adapter by lazy {
        VehicleAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        adapter.itemClicked = object: ListItemClicked{
            override fun itemClicked(item: Vehicle) {
                var intent = Intent(this@VehicleActivity, SecondActivity::class.java)
                intent.putExtra("vehicle", item)
                startActivity(intent)
            }

        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        EspressoIdlingResource.increment()
        viewModel.liveData.observe(this){response ->
            when(response.status){
                ApiResult.Status.SUCCESS -> {
                    response.data?.let {
                        showData(it)
                    }?: run {
                        showError()
                    }
                    EspressoIdlingResource.decrement()
                }
                ApiResult.Status.ERROR ->{
                    response.data?.let {
                        showData(it)
                    }?: run {
                        showError()
                    }
                    EspressoIdlingResource.decrement()
                }
                ApiResult.Status.LOADING -> {
                    response.data?.let {
                        if(it.isEmpty()){
                            showLoading()
                        }else {
                            showData(it)
                        }
                    }?: run {
                        showLoading()
                    }
                }
            }
        }

        viewModel.getData()
    }

    fun showData(list: List<Vehicle>){
        binding.fullScreenview.hide(FullScreenViewType.ErrorView)
        binding.fullScreenview.hide(FullScreenViewType.LoadingView)
        binding.recyclerView.isVisible = true
        adapter.swapData(list)
    }

    fun showError(){
        binding.fullScreenview.show(FullScreenViewType.ErrorView)
        binding.fullScreenview.hide(FullScreenViewType.LoadingView)
        binding.recyclerView.isVisible = false
    }

    fun showLoading(){
        binding.fullScreenview.hide(FullScreenViewType.ErrorView)
        binding.fullScreenview.show(FullScreenViewType.LoadingView)
        binding.recyclerView.isVisible = false
    }

}